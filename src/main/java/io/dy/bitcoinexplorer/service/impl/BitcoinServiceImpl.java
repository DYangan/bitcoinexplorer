package io.dy.bitcoinexplorer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.dy.bitcoinexplorer.api.BitcoinRestApi;
import io.dy.bitcoinexplorer.dao.BlockMapper;
import io.dy.bitcoinexplorer.dao.TransacationMapper;
import io.dy.bitcoinexplorer.po.Block;
import io.dy.bitcoinexplorer.po.Transacation;
import io.dy.bitcoinexplorer.service.BitcoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Async
@Transactional
public class BitcoinServiceImpl implements BitcoinService{
    private Logger logger=LoggerFactory.getLogger(this.getClass());
   @Autowired
   private BitcoinRestApi  bitcoinRestApi;
    @Autowired
    private BlockMapper  blockMapper;
    @Autowired
    private TransacationMapper  transacationMapper;
    @Override
    @Async
    public void syncBlock(String blockhash) {
        logger.info("begin  to sync  block from {}",blockhash);
        String tempBlockhash=blockhash;
        while (tempBlockhash !=null && !tempBlockhash.isEmpty()){
            JSONObject blockJson = bitcoinRestApi.getBlock(tempBlockhash);
            Block block= new Block();
            block.setBlockhash(blockJson.getString("hash"));
            block.setHeight(blockJson.getInteger("height"));
            Long timestamp = blockJson.getLong("time");
            Date time=new Date(timestamp*1000);
            block.setTime(time);
            block.setTxsize(blockJson.getShort("nTx"));
            block.setSize(blockJson.getInteger("size"));
            block.setWeight(blockJson.getFloat("weight"));
            block.setDifficulty(blockJson.getDouble("version"));
            //前后各一个
            block.setPrevBlock(blockJson.getString("previousblockhash"));
            block.setNextBlock(blockJson.getString("nextblockhash"));
            Integer confirmations = blockJson.getInteger("confirmations");
            blockMapper.insert(block);
            //todo sync   txes
            JSONArray tx = blockJson.getJSONArray("tx");
            for (Object o : tx) {
                JSONObject jsonObject = new JSONObject((LinkedHashMap) o);
                syncTx(jsonObject, tempBlockhash, time, confirmations);
            }
            tempBlockhash = block.getNextBlock();
        }
        logger.info("end sync block ");
    }

    @Override
    public void syncTx(JSONObject txJson,String blockhash,Date time,Integer confirmations) {
        Transacation tx = new Transacation();
        tx.setTxhash(txJson.getString("txid"));
        tx.setBlockhash(blockhash);
        tx.setTime(time);
        //todo  set  tx  amount
        //数量

        tx.setSize(txJson.getInteger("size"));
        tx.setWeight(txJson.getFloat("weight"));
        tx.setConfirmations(confirmations);

    }

    /**
     *交易费用
     * @param txjson
     */
    @Override
    @Transactional
    public void syncTxDetail(JSONObject txjson) {
        JSONArray vout = txjson.getJSONArray("vout");
        syncDetailvout(vout);
        JSONArray vin = txjson.getJSONArray("vin");
        syncDetailvin(vin);
    }

    /**
     *详情
     * @param vins
     */
    @Override
    public void syncDetailvin(JSONArray vins) {

    }

    /**
     *
     * @param vouts
     */
    @Override
    public void syncDetailvout(JSONArray vouts) {
        for (Object vout : vouts) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) vout);
        }
    }

}
