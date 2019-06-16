## 1 获取当前最新区块
url:/block/geRecenBlocks
response:
```json
{
        "blockhash": "0000000000000000001a2f2ef252814df0dae4ae7f6b76014754e2bc934082d6",
        "height": 580776,
        "time": "2019-06-15T02:04:14.966+0000",
        "txsize": 2382,
        "size": 1133699
    },
    {
        "blockhash": "00000000000000000013a3301ae78e8898659409188fbf3f39085b04971ecb44",
        "height": 580775,
        "time": "2019-06-15T02:04:14.966+0000",
        "txsize": 3373,
        "size": 1178521
    }
```

| ResponseField     |     Type |   Description   | 
| :--------------: | :--------:| :------: |
|    blockhash|   String | 区块hash|
|    height|   Integer | 区块高度 |
|    time|   Date |  出快时间 |
|    txsize|   Short |  交易数量 |
|    size|   Integer | 区块大小|


## 2  根据区块hash获取区块详情
url:/block/getByBlockhash?blcokhash={blockhash}
response:
```json
{
    "blockhash": "0000000000000000000e9a32f6ad64b425c3aaa2da608098629203c42f3bca06",
    "height": 580785,
    "preBlock": "00000000000000000014bf1693dfb2493e0b32ac00fbdb96f04c4d3c2add197e",
    "nextBlock": "00000000000000000020baaa1796d87bd84fdfb150d9dc87bbf4c4e6b4e287b3",
    "merkleRoot": "26cc730cdab9f4a1fc0b79a5d6878e31cb28a31f97fc5a1d5218ef05788901d3",
    "txSize": 3028,
    "outputTotal": null,
    "fees": 1101424.86,
    "time": 1560682589991,
    "difficulty": 7409399249090.25,
    "size": 1233505
}
```

| ResponseField     |     Type |   Description   | 
| :--------------: | :--------:| :------: |
|    blockhash|   String | 区块hash|
|    height|   Integer | 区块高度 |
|    preBlock|   String | 前一个区块|
|    nextBlock|   String |  后一个区块 |
|    merkleRoot|   String | 梅尔克树|
|    txSize|   Short | 交易数量|
|    outputTotal|   Double | 总输出 |
|    fees|   Double |  交易费用 |
|    time|   Long |  出块时间 |
|    difficulty|   Integer | 难度系数|
|    size|   Integer | 区块大小|


## 3 根据区块高度获取区块详情
url:block/getByHeight?height={height}
response:
```json
{
    "blockhash": "0000000000000000000e9a32f6ad64b425c3aaa2da608098629203c42f3bca06",
    "height": 580785,
    "preBlock": "00000000000000000014bf1693dfb2493e0b32ac00fbdb96f04c4d3c2add197e",
    "nextBlock": "00000000000000000020baaa1796d87bd84fdfb150d9dc87bbf4c4e6b4e287b3",
    "merkleRoot": "26cc730cdab9f4a1fc0b79a5d6878e31cb28a31f97fc5a1d5218ef05788901d3",
    "txSize": 3028,
    "outputTotal": null,
    "fees": 1101424.86,
    "time": 1560683708837,
    "difficulty": 7409399249090.25,
    "size": 1233505
}
```

| ResponseField     |     Type |   Description   | 
| :--------------: | :--------:| :------: |
|    blockhash|   String | 区块hash|
|    height|   Integer | 区块高度 |
|    preBlock|   String | 前一个区块|
|    nextBlock|   String |  后一个区块 |
|    merkleRoot|   String | 梅尔克树|
|    txSize|   Short | 交易数量|
|    outputTotal|   Double | 总输出 |
|    fees|   Double |  交易费用 |
|    time|   Long |  出块时间 |
|    difficulty|   Integer | 难度系数|
|    size|   Integer | 区块大小|

