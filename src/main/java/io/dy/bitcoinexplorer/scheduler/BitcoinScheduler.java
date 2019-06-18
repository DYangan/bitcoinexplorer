package io.dy.bitcoinexplorer.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BitcoinScheduler {
    /**
     * 测试同步
     */
    private Logger  logger= LoggerFactory.getLogger(this.getClass());
    @Scheduled(fixedRate = 10*60*1000)
    public  void  syncData()
    {
        logger.info("begin to sync  bitcoin data");
    }
}
