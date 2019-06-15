package io.dy.bitcoinexplorer.dao;

import io.dy.bitcoinexplorer.po.TxDetall;

public interface TxDetallMapper {
    int deleteByPrimaryKey(Long txDetallId);

    int insert(TxDetall record);

    int insertSelective(TxDetall record);

    TxDetall selectByPrimaryKey(Long txDetallId);

    int updateByPrimaryKeySelective(TxDetall record);

    int updateByPrimaryKey(TxDetall record);
}