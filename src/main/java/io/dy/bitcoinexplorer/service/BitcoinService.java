package io.dy.bitcoinexplorer.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public interface BitcoinService {
    void  syncBlockchainFromHash(String blockhash) throws  Throwable;
    String syncBlock(String  blockhash ) throws Throwable;
    void  syncTx(JSONObject txJson, String blockhash, Date time,Integer confirmations) throws Throwable;
    void syncTxDetail(JSONObject txjson,String txid) throws  Throwable;

    void syncDetailvout(JSONArray vouts,String txid);

    void syncDetailvin(JSONArray vins,String txid) throws  Throwable;
}
