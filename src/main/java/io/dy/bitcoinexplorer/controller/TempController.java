package io.dy.bitcoinexplorer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.dy.bitcoinexplorer.api.BitcoinRestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")

public class TempController {
    @Autowired
    private BitcoinRestApi  bitcoinRestApi;
    @GetMapping("/getBlockChainInfo")
    public String  getBlockChainInfo(){
        JSONObject blockChainInfo = bitcoinRestApi.getBlockChainInfo();
        return  blockChainInfo.toJSONString();
    }
    @GetMapping("/getBlockByHash")
    public String getBlockByHash() {
        JSONObject blockChainInfo = bitcoinRestApi.getBlockByHash("0000000002a7ad62305aafb3f2d02bad31df3c59e0b87e91d0ae95a5b951f1c1");
        return blockChainInfo.toJSONString();
    }
    @GetMapping("/getBlockNotDetails")
    public String getBlockNotDetails() {
        JSONObject blockChainInfo = bitcoinRestApi.getBlockNotDetails("0000000002a7ad62305aafb3f2d02bad31df3c59e0b87e91d0ae95a5b951f1c1");
        return blockChainInfo.toJSONString();
    }

    @GetMapping("/getBlockHeaders")
    public JSONArray getBlockHeaders() {
        JSONArray blockChainInfo = bitcoinRestApi.getBlockHeaders(5,"000000000006b2fa68b335984976a20a7414b50f8d9320696646da6bb2cb51d8");
        return blockChainInfo;
    }
    @GetMapping("/getTransaction")
    public String getTransaction(){
        JSONObject blockChainInfo = bitcoinRestApi.getTransaction("f0283db5fe2f5dae61640cdd53571512328dace852470bd107bb2d7bd2a56976");
        return blockChainInfo.toJSONString();
    }
    @GetMapping("/getBlockByHeight")
    public String test(){
        JSONObject blockChainInfo = bitcoinRestApi.getBlockByHeight(207341);
        return blockChainInfo.toJSONString();
    }
    @GetMapping("/getMempoolInfo")
    public String getMempoolInfo(){
        JSONObject blockChainInfo = bitcoinRestApi.getMempoolInfo();
        return blockChainInfo.toJSONString();
    }
    @GetMapping("/getMempoolContents")
    public String getMempoolContents(){
        JSONObject blockChainInfo = bitcoinRestApi.getMempoolContents();
        return blockChainInfo.toJSONString();
    }


}
