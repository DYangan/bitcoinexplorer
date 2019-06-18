package io.dy.bitcoinexplorer.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public interface BitcoinService {
    void  syncBlock(String  blockhash );
    void  syncTx(JSONObject txJson, String blockhash, Date time,Integer confirmations);
    void syncTxDetail(JSONObject txjson);

    void syncDetailvout(JSONArray vouts);

    void syncDetailvin(JSONArray vins);
}
