package io.dy.bitcoinexplorer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.dy.bitcoinexplorer.api.BitcoinJsonRpcApi;
import io.dy.bitcoinexplorer.api.BitcoinRestApi;
import io.dy.bitcoinexplorer.dao.BlockMapper;
import io.dy.bitcoinexplorer.dao.TransacationMapper;
import io.dy.bitcoinexplorer.dao.TxDetallMapper;
import io.dy.bitcoinexplorer.enumeration.TxDetailType;
import io.dy.bitcoinexplorer.po.Block;
import io.dy.bitcoinexplorer.po.Transacation;
import io.dy.bitcoinexplorer.po.TxDetall;
import io.dy.bitcoinexplorer.service.BitcoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
@Service
public class BitcoinServiceImpl implements BitcoinService{
    private Logger logger=LoggerFactory.getLogger(this.getClass());
   @Autowired
   private BitcoinRestApi  bitcoinRestApi;
    @Autowired
    private BlockMapper  blockMapper;
    @Autowired
    private TransacationMapper  transacationMapper;
    @Autowired
    private BitcoinJsonRpcApi  bitcoinJsonRpcApi;
    @Autowired
    private TxDetallMapper txDetallMapper;

    @Override
    @Async
    public void syncBlockchainFromHash(String blockhash) throws Throwable {
        logger.info("begin to sync blockchain from {}", blockhash);
        String tempBlockhash = blockhash;
        while (tempBlockhash != null && !tempBlockhash.isEmpty()){

            String nextBlock = syncBlock(tempBlockhash);
            tempBlockhash = nextBlock;
        }
        logger.info("end sync blockchain");
    }

    @Override
    @Transactional
    public String syncBlock(String blockhash) throws Throwable {
        JSONObject blockJson = bitcoinRestApi.getBlock(blockhash);
        Block block = new Block();
        block.setBlockhash(blockJson.getString("hash"));
        block.setHeight(blockJson.getInteger("height"));
        Long timestamp = blockJson.getLong("time");
        Date time = new Date(timestamp * 1000);
        block.setTime(time);
        block.setTxsize(blockJson.getShort("nTx"));
        block.setSize(blockJson.getInteger("size"));
        block.setWeight(blockJson.getFloat("weight"));
        block.setDifficulty(blockJson.getDouble("version"));
        block.setPrevBlock(blockJson.getString("previousblockhash"));
        block.setNextBlock(blockJson.getString("nextblockhash"));
        Integer confirmations = blockJson.getInteger("confirmations");
        blockMapper.insert(block);

        JSONArray txesArray = blockJson.getJSONArray("tx");

        for (Object txObj : txesArray) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) txObj);
            syncTx(jsonObject, blockhash, time, confirmations);
        }

        return block.getNextBlock();
    }

    @Override
    @Transactional
    public void syncTx(JSONObject txJson,String blockhash,Date time,Integer confirmations) throws  Throwable {
        Transacation tx = new Transacation();
        String txid=txJson.getString("txid");
        tx.setTxhash(txid);
        tx.setBlockhash(blockhash);
        tx.setTime(time);
        tx.setSize(txJson.getInteger("size"));
        tx.setWeight(txJson.getFloat("weight"));
        tx.setConfirmations(confirmations);
        transacationMapper.insert(tx);
        //todo  set  tx  amount

        syncTxDetail(txJson,txid);
        //数量
    }

    @Override
    @Transactional
    public void syncTxDetail(JSONObject txjson,String txid) throws  Throwable {
        JSONArray vout = txjson.getJSONArray("vout");
        syncDetailvout(vout,txid);
        JSONArray vin = txjson.getJSONArray("vin");
        syncDetailvin(vin,txid);
    }

    @Override
    @Transactional
    public void syncDetailvout(JSONArray vouts, String txid) {
        for (Object vout : vouts) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) vout);
            TxDetall txDetail = new TxDetall();
            txDetail.setAmount(jsonObject.getDouble("value"));
            txDetail.setType((byte) TxDetailType.Receive.ordinal());
            JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
            JSONArray addresses = scriptPubKey.getJSONArray("address");
            if (addresses != null){
                String address = addresses.getString(0);
                txDetail.setAdress(address);
            }
            txDetallMapper.insert(txDetail);
        }

    }

    @Override
    @Transactional
    public void syncDetailvin(JSONArray vins, String txid) throws Throwable {
        for (Object vin : vins) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) vin);
            String vinTxid = jsonObject.getString("txid");
            Integer n = jsonObject.getInteger("vout");

            if (vinTxid != null){
                JSONObject vinTxJson = bitcoinJsonRpcApi.getTransactionById(vinTxid);
                JSONArray vouts = vinTxJson.getJSONArray("vout");
                JSONObject utxoJson = vouts.getJSONObject(n);

                TxDetall txDetail = new TxDetall();
                txDetail.setAmount(-utxoJson.getDouble("value"));
                txDetail.setTxhash(txid);
                txDetail.setType((byte) TxDetailType.Send.ordinal());
                JSONObject scriptPubKey = utxoJson.getJSONObject("scriptPubKey");
                JSONArray addresses = scriptPubKey.getJSONArray("addresses");
                if (addresses != null){
                    String address = addresses.getString(0);
                    txDetail.setAdress(address);
                }
                txDetallMapper.insert(txDetail);
            }
        }
    }
}


