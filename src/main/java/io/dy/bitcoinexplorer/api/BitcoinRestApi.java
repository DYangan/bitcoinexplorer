package io.dy.bitcoinexplorer.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BitcoinRestApi",url = "http://localhost:18332")
public interface BitcoinRestApi {
    @GetMapping("/rest/chaininfo.json")
    JSONObject getBlockChainInfo();

    @GetMapping("/rest/block/{blockhash}.json")
    JSONObject getBlockByHash(@PathVariable("blockhash") String blockhash);

    @GetMapping("/rest/block/notxdetails/{blockhash}.json")
    JSONObject getBlockNotDetails(@PathVariable String blockhash);

    @GetMapping("/rest/headers/{count}/{blockhash}.json")
    JSONArray getBlockHeaders(@PathVariable("count") Integer count, @PathVariable("blockhash") String blockhash);

    @GetMapping("/rest/tx/{txhash}.json")
    JSONObject getTransaction(@PathVariable("txhash") String txhash);

    @GetMapping("/rest/blockhashbyheight/{height}.json")
    JSONObject getBlockByHeight(@PathVariable("height") Integer height);

    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempoolInfo();

    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();


    @GetMapping("/rest/getutxos/{txid}-{n}.json")
    JSONObject getUTXO(@PathVariable String txid, @PathVariable Integer n);

    @GetMapping("/rest/getutxos/checkmempool/{txid}-{n}.json")
    JSONObject getUTXOCheckMempool(@PathVariable String txid, @PathVariable Integer n);
}
