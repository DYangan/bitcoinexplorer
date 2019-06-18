package io.dy.bitcoinexplorer.api;


import com.alibaba.fastjson.JSONObject;

public interface BitcoinJsonRpcApi {
     JSONObject getBlockChainInfo() throws   Throwable;

     JSONObject getBlockByHash(String blockhash)throws Throwable;

     JSONObject getTransactionById(String txid) throws Throwable;

     String getBlockhashByHeight(Integer height) throws Throwable;

}
