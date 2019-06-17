package io.dy.bitcoinexplorer.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import io.dy.bitcoinexplorer.api.BitcoinJsonRpcApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
    @Service
    @Component
    public class BitcoinJsonRpcApiImpl implements BitcoinJsonRpcApi {
        private JsonRpcHttpClient jsonRpcHttpClient;
        public BitcoinJsonRpcApiImpl(@Value("${bitcoin.jsonrpc.username}") String username,
                                     @Value("${bitcoin.jsonrpc.password}") String password,
                                     @Value("${bitcoin.jsonrpc.url}") String url) throws MalformedURLException {
            HashMap<String, String> headers = new HashMap<>();
            String authStrOrig = String.format("%s:%s",username,password);
            String authStr = Base64.getEncoder().encodeToString(authStrOrig.getBytes());
            String authStrResult = String.format("Basic %s",authStr);
            headers.put("Authorization",authStrResult);
            jsonRpcHttpClient = new JsonRpcHttpClient(new URL(url),headers);
        }
        //区块链详情
        @Override
        public JSONObject getBlockChainInfo() throws Throwable {
            //getbestblockhash
            JSONObject  jsonObject = jsonRpcHttpClient.invoke("getblockchaininfo", new Object[]{}, JSONObject.class);
            return jsonObject;
        }

        @Override
        public JSONObject getBlockByHash(String s) throws Throwable{
            JSONObject  jsonObject = jsonRpcHttpClient.invoke("getbestblockhash", new Object[]{}, JSONObject.class);
            return jsonObject;
        }

        @Override
        public JSONObject getTransactionById(String txid) throws Throwable {
            JSONObject jsonObject = jsonRpcHttpClient.invoke("getrawtransaction", new Object[]{txid, true}, JSONObject.class);
            return jsonObject;
        }

        @Override
        public String getBlockhashByHeight(Integer height) throws Throwable {
            String result = jsonRpcHttpClient.invoke("getblockhash", new Object[]{height}, String.class);
            return result;
        }
    }
