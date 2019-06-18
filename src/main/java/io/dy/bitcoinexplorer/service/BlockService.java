package io.dy.bitcoinexplorer.service;

import io.dy.bitcoinexplorer.dto.BlockListDTO;

import java.util.List;

public interface BlockService {
    List<BlockListDTO> getRecentBlocks();
}
