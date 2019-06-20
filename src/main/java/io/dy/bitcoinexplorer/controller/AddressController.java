package io.dy.bitcoinexplorer.controller;

import io.dy.bitcoinexplorer.dao.TxDetallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    /**
     * 余额
     * @param address
     * @return
     */
    @Autowired
    private TxDetallMapper txDetallMapper;
    @GetMapping("/getBalance")
    public Double  getBalance(@RequestParam  String  address){
        Double balance = txDetallMapper.getBalance(address);
        return  balance;
    }
}
