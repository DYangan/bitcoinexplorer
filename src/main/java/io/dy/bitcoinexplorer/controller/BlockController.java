package io.dy.bitcoinexplorer.controller;

import io.dy.bitcoinexplorer.dto.BlockGetDTO;
import io.dy.bitcoinexplorer.dto.BlockListDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController
{
            @GetMapping("/geRecenBlocks")
            public List<BlockListDTO> geRecenBlocks(){
                ArrayList<BlockListDTO> blockListDTOS = new ArrayList<>();
                BlockListDTO blockListDTO = new BlockListDTO();
                blockListDTO.setBlockhash("0000000000000000001a2f2ef252814df0dae4ae7f6b76014754e2bc934082d6");
                blockListDTO.setHeight(580776);
                blockListDTO.setTime(new Date());
                blockListDTO.setTxsize((short)2382);
                blockListDTO.setSize(1133699);
                blockListDTOS.add(blockListDTO);

                BlockListDTO blockListDTO2 = new BlockListDTO();
                blockListDTO2.setBlockhash("00000000000000000013a3301ae78e8898659409188fbf3f39085b04971ecb44");
                blockListDTO2.setHeight(580775);
                blockListDTO2.setTime(new Date());
                blockListDTO2.setTxsize((short)3373);
                blockListDTO2.setSize(1178521);
                blockListDTOS.add(blockListDTO2);
                return  blockListDTOS;
            }
            @GetMapping("/getByBlockhash")
             public BlockGetDTO getByBlockhash(@RequestParam String blcokhash){
                BlockGetDTO blockGetDTO = new BlockGetDTO();
                blockGetDTO.setBlockhash("0000000000000000000e9a32f6ad64b425c3aaa2da608098629203c42f3bca06");
                blockGetDTO.setHeight(580785);
                blockGetDTO.setPreBlock("00000000000000000014bf1693dfb2493e0b32ac00fbdb96f04c4d3c2add197e");
                blockGetDTO.setNextBlock("00000000000000000020baaa1796d87bd84fdfb150d9dc87bbf4c4e6b4e287b3");
                blockGetDTO.setMerkleRoot("26cc730cdab9f4a1fc0b79a5d6878e31cb28a31f97fc5a1d5218ef05788901d3");
                blockGetDTO.setTime(new Date().getTime());
                blockGetDTO.setFees(1101424.86);
                blockGetDTO.setTxSize((short) 3028);
                blockGetDTO.setSize(1233505);
                blockGetDTO.setDifficulty(7409399249090.25);
                return  blockGetDTO;
        }
            @GetMapping("/getByHeight")
            public BlockGetDTO  getByHeight(@RequestParam Integer height){
                BlockGetDTO blockGetDTO = new BlockGetDTO();
                blockGetDTO.setBlockhash("0000000000000000000e9a32f6ad64b425c3aaa2da608098629203c42f3bca06");
                blockGetDTO.setHeight(580785);
                blockGetDTO.setPreBlock("00000000000000000014bf1693dfb2493e0b32ac00fbdb96f04c4d3c2add197e");
                blockGetDTO.setNextBlock("00000000000000000020baaa1796d87bd84fdfb150d9dc87bbf4c4e6b4e287b3");
                blockGetDTO.setMerkleRoot("26cc730cdab9f4a1fc0b79a5d6878e31cb28a31f97fc5a1d5218ef05788901d3");
                blockGetDTO.setTime(new Date().getTime());
                blockGetDTO.setFees(1101424.86);
                blockGetDTO.setTxSize((short) 3028);
                blockGetDTO.setSize(1233505);
                blockGetDTO.setDifficulty(7409399249090.25);
                return  blockGetDTO;
            }
}
