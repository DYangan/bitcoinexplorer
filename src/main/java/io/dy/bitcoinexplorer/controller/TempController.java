package io.dy.bitcoinexplorer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import io.dy.bitcoinexplorer.api.BitcoinJsonRpcApi;
import io.dy.bitcoinexplorer.api.BitcoinRestApi;
import io.dy.bitcoinexplorer.api.impl.BitcoinJsonRpcApiImpl;
import io.dy.bitcoinexplorer.dao.BlockMapper;
import io.dy.bitcoinexplorer.po.Block;
import io.dy.bitcoinexplorer.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("/temp")
@EnableAutoConfiguration
public class TempController {
@Autowired
  private BitcoinRestApi  bitcoinRestApi;
@Autowired
 private BitcoinJsonRpcApi  bitcoinJsonRpcApi;
@Autowired
private BlockMapper  blockMapper;
@Autowired
private BitcoinService  bitcoinService;
//    @GetMapping("/getBlockChainInfo")
//    public String  getBlockChainInfo(){
//        JSONObject blockChainInfo = bitcoinRestApi.getBlockChainInfo();
//        return  blockChainInfo.toJSONString();
//    }
//    @GetMapping("/getBlockByHash")
//    public String getBlockByHash() {
//        JSONObject blockChainInfo = bitcoinRestApi.getBlockByHash("0000000002a7ad62305aafb3f2d02bad31df3c59e0b87e91d0ae95a5b951f1c1");
//        return blockChainInfo.toJSONString();
//    }
//    @GetMapping("/getBlockNotDetails")
//    public String getBlockNotDetails() {
//        JSONObject blockChainInfo = bitcoinRestApi.getBlockNotDetails("0000000002a7ad62305aafb3f2d02bad31df3c59e0b87e91d0ae95a5b951f1c1");
//        return blockChainInfo.toJSONString();
//    }
//
//    @GetMapping("/getBlockHeaders")
//    public JSONArray getBlockHeaders() {
//        JSONArray blockChainInfo = bitcoinRestApi.getBlockHeaders(5,"000000000006b2fa68b335984976a20a7414b50f8d9320696646da6bb2cb51d8");
//        return blockChainInfo;
//    }
//    @GetMapping("/getTransaction")
//    public String getTransaction(){
//        JSONObject blockChainInfo = bitcoinRestApi.getTransaction("f0283db5fe2f5dae61640cdd53571512328dace852470bd107bb2d7bd2a56976");
//        return blockChainInfo.toJSONString();
//    }
//    @GetMapping("/getBlockByHeight")
//    public String test(){
//        JSONObject blockChainInfo = bitcoinRestApi.getBlockByHeight(207341);
//        return blockChainInfo.toJSONString();
//    }
//    @GetMapping("/getMempoolInfo")
//    public String getMempoolInfo(){
//        JSONObject blockChainInfo = bitcoinRestApi.getMempoolInfo();
//        return blockChainInfo.toJSONString();
//    }
//    @GetMapping("/getMempoolContents")
//    public String getMempoolContents(){
//        JSONObject blockChainInfo = bitcoinRestApi.getMempoolContents();
//        return blockChainInfo.toJSONString();
//    }

    @GetMapping("/test")
    private  String test() throws  Throwable{
        //JSONObject blockChainInfo = bitcoinJsonRpcApi.getBlockChainInfo();
       // JSONObject block = bitcoinJsonRpcApi.getBlockByHash("0000000000000119c7cef78c8d508e0e1b36a680baf89a659d14c224315e238b");
       // JSONObject transaction = bitcoinJsonRpcApi.getTransactionById("1d7d5226bb2d39e328262e9816694458d2ae081af6e380790bdc00b968ce0daf");

        //JSONObject utxo = bitcoinRestApi.getUTXO("1d7d5226bb2d39e328262e9816694458d2ae081af6e380790bdc00b968ce0daf", 0);
        //JSONObject utxoCheckMempool = bitcoinRestApi.getUTXOCheckMempool("0b9a0ea6c034834e79db101967985e1b0d6358cad111444ff52075106acba8d6", 0);

        String  tempBlockhash="00000000000000bb8d77e0c70458312cf4a4baecacecb00892802973bf201ac7";

        bitcoinService.syncBlock(tempBlockhash);

        return  null;

    }

}
