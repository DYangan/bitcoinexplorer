SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for transacation
-- ----------------------------
DROP TABLE IF EXISTS `transacation`;
CREATE TABLE `transacation`
(
  `txhash`   char(64) NOT NULL,
  `time`     datetime,
  `amout`    double,
  `size`     int,
  `weight`   float,
  `blockhash` char(64) NOT NULL,
  `confirmations` int,
  PRIMARY KEY (`txhash`),
  index `idx_txhash` (`txhash`),
  index `idx_time` (`time`),
  index `blockhash` (`blockhash`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
