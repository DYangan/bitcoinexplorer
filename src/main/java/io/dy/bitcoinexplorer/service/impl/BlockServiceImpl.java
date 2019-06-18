package io.dy.bitcoinexplorer.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.dy.bitcoinexplorer.dao.BlockMapper;
import io.dy.bitcoinexplorer.dto.BlockListDTO;
import io.dy.bitcoinexplorer.po.Block;
import io.dy.bitcoinexplorer.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class BlockServiceImpl implements BlockService {
    @Autowired
    private BlockMapper  blockMapper;

    @Override
    public List<BlockListDTO> getRecentBlocks() {
        ArrayList<BlockListDTO>  blockListDTOS = new ArrayList<>();
        List<Block> blocks = blockMapper.selectRecentBlocks();
        for (Block block : blocks) {
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setBlockhash(block.getBlockhash());
            blockListDTO.setHeight(block.getHeight());
            blockListDTO.setTime(block.getTime().getTime());
            blockListDTO.setTxsize(block.getTxsize());
            //todo
            blockListDTO.setSize(block.getSize());
            blockListDTOS.add(blockListDTO);
        }

        return blockListDTOS;
    }
}
