pragma solidity ^0.4.22;

contract Auction{
    //是否已经结束
    bool ended = false;
    //开始时间
    uint256 startTime;
    //结束时间
    uint256 endTime;
    //合约创建人
    address beneficiary;
    //竞拍最高者地址
    address highestBidder;
    //竞拍最高价
    uint256 highestBid = 0;
    uint256 blockLow;
    uint256 blockMax;
    //打块时间
    uint256 blockTime;
    //当前时间
    uint256 currentTime;
    //当前块数量
    uint256 currentBlockNum;
    //开始价格
    uint256 bidStartingPrice;
    //递增价格
    uint256 increasePrice;
    //画的tokenid
    string tokenID;

    struct AuctionInfo{
        //竞拍价格
      uint256 bidPrice;
        //竞拍次数
      uint256 bidFrequency;
        //是否存在
      bool isExist;
    }
    //竞拍人
    address[] auctionUser = new address[](1);
    mapping(address => AuctionInfo) pendingReturns;


    constructor(string _tokenID,uint256 _startTime,
      uint256 _endTime,uint256 _blockTime,uint256 _bidStartingPrice,
      uint256 _increasePrice) public {
        //初始化合约创建人
      beneficiary = msg.sender;
        //初始化当前失败
      currentTime = now;
        //获得当前块得高度
      currentBlockNum = block.number;
        //竞拍起始价格
      bidStartingPrice = _bidStartingPrice;
        //递增价格
      increasePrice = _increasePrice;
        //开始时间
      startTime = _startTime;
        //结束时间
      endTime = _endTime;
        //画的tokenid
      tokenID = _tokenID;
        //打快速度
      blockTime = _blockTime;
        //如果开始时间小于结束时间
      if(startTime <endTime){
          uint256 tempStart = 0;
          //如果开始时间大于当前时间
          if(startTime>currentTime){
              //开始时间减去当前时间
            tempStart = startTime - currentTime;
          }
          //结束时间减去当前时间
          uint256 tempEnd = endTime - currentTime;
            //距离开始时间 能打的块数量
          uint256 tempStartNum = tempStart/blockTime;
          //当前时间距离结束时间 可以打的块数
          uint256 tempEndNum = tempEnd/blockTime;

          blockLow = currentBlockNum + tempStartNum;
          blockMax = currentBlockNum + tempEndNum;
      }
    }
    event testData(uint256 num,address tempaddrs);
    //_bigPrice：竞拍价格
    function auctionBid(uint256 _bigPrice)  public payable returns(uint256){
      /* str2 = _str2; */
        //竞拍者不能是自己
      if(beneficiary != msg.sender){
          //块数量要大于等于最低数
        if(block.number >= blockLow){
            //块数量小于等于最大数
            if(block.number <= blockMax){
                //最高价小于当前竞拍价 而且要大于起始价格
                if(highestBid < _bigPrice && bidStartingPrice<_bigPrice){
                  uint256 differencePrice;
                  uint256 computerBidPrice;
                  uint256 tempBidFrequency;
                    //如果不是第一次竞拍
                  if(pendingReturns[msg.sender].isExist){
                      //根据地址获取对象信息
                    AuctionInfo storage auctionInfo = pendingReturns[msg.sender];
                      //获得上一次出价
                    uint256 tempbidPrice = auctionInfo.bidPrice;
                      //当前出价减去上一次出价
                    differencePrice = _bigPrice - tempbidPrice;
                      //获得差价
                    computerBidPrice = differencePrice;
                      //获得竞拍次数
                    tempBidFrequency = auctionInfo.bidFrequency;
                      //竞拍次数+1
                    tempBidFrequency = tempBidFrequency + 1;
                      //重新把对象搞进去
                    pendingReturns[msg.sender] = AuctionInfo(_bigPrice,tempBidFrequency,true);
                  }else{
                      //如果不存在，计算价格就是第一次竞拍价
                    computerBidPrice = _bigPrice;
                      //递增价格
                    differencePrice = _bigPrice - highestBid;
                    tempBidFrequency = 1;
                    pendingReturns[msg.sender] = AuctionInfo(_bigPrice,tempBidFrequency,true);
                      //存入有哪些竞拍人
                    auctionUser.push(msg.sender);
                  }
                  if(msg.sender.balance >= computerBidPrice){
                    if(differencePrice >= increasePrice){
                      highestBid = _bigPrice;
                      highestBidder = msg.sender;

                      beneficiary.transfer(computerBidPrice);


                      return 0;
                    }else{
                        return 6;
                    }
                  }else{
                      return 5;
                  }
                }else{
                    return 4;
                }
            }else{
                return 3;
            }
        }else{
            return 2;
        }
      }else{
          return 1;
      }

    }

    //结束竞拍  必须是创建合约的人才能结束
    function auctionEnd() public payable returns(uint256){
      if(msg.sender == beneficiary){
          if(block.number > blockMax){
            if(!ended){
              ended = true;

              uint256 len = auctionUser.length;
              for(uint256 i=1;i<len;i++){
                  address tempUserAddr = auctionUser[i];
                  if(tempUserAddr != highestBidder){
                    AuctionInfo storage auctionInfo = pendingReturns[tempUserAddr];
                    uint256 tempPrice = auctionInfo.bidPrice;
                    tempUserAddr.transfer(tempPrice);
                  }
              }
              return 0;
            }else{
              return 9;
            }
          }else{
            return 8;
          }
      }else{
          return 7;
      }

    }


    function getMaxPrice() public view returns(uint256){
        return highestBid;
    }
    function getMaxAddress() public view returns(address){
        return highestBidder;
    }
    function getBeneficiary() public view returns(address){
        return beneficiary;
    }
    function getMsgSender() public view returns(address){
        return msg.sender;
    }
    function getblocknumber() public view returns(uint256){
        return block.number;
    }

    function getblockMax() public view returns(uint256){
        return blockMax;
    }
    function getblockLow() public view returns(uint256){
        return blockLow;
    }
    function getcurrentTime() public view returns(uint256){
        return currentTime;
    }
    function getstartTime() public view returns(uint256){
        return startTime;
    }
    function getendTime() public view returns(uint256){
        return endTime;
    }
    function getTokenID() public view returns(string){
        return tokenID;
    }


}
