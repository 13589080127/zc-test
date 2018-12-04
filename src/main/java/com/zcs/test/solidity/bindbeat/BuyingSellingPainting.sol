pragma solidity ^0.4.22;

contract BuyingSellingPainting{

    address owner;
    uint256 startSign;
    uint256 endSign;

    struct PaintingInfo{
       //画的token
      string paintingToken;
      uint256 sellPrice;
      address sellAddr;
      uint256 commPrice;
      address buyAddr;
      bool isSell;
      bool isCancel;
    }
    mapping(uint256 => PaintingInfo) paintingInfo;
    
    constructor(uint256 _startSign,uint256 _endSign) public{
      owner = msg.sender;
      startSign = _startSign;
      endSign = _endSign;
    }
    //卖画的方法 画的id，画的token，售价，佣金
    function sellPainting(uint256 _paintingId,string _paintingToken,uint256 _sellPrice,uint256 _commissionPrice) public {
      require(startSign<=_paintingId,"1");
      require(_paintingId<=endSign,"2");
      //画的token，售价，拥有者，佣金，卖的地址，是否售出了，是否回滚了
      houseSellInfo[_paintingId] = PaintingInfo(_paintingToken,_sellPrice,msg.sender,_commissionPrice,msg.sender,false,false);
    }
    //回滚画 ，画的id
    function cancelPainting(uint256 _paintingId) public {
      require(startSign<=_paintingId,"3");
      require(_paintingId<=endSign,"4");
      bool tempIsSell = paintingInfo[_paintingId].isSell;
      require(!tempIsSell,"5");
      paintingInfo[_paintingId].isCancel = true;
    }
    //买房子
    function buyPainting(uint256 _paintingId) public payable{
        require(startSign<=_paintingId,"6");
        require(_paintingId<=endSign,"7");
        //售卖价格
        uint256 tempSellPrice = paintingInfo[_paintingId].sellPrice;
        //售卖人
        address tempSellAddr = paintingInfo[_paintingId].sellAddr;
        //是否购买
        bool tempIsSell = paintingInfo[_paintingId].isSell;
        //是否回滚
        bool tempIsCancel = paintingInfo[_paintingId].isCancel;
        require(tempSellAddr != msg.sender,"8");
        require(msg.sender.balance > tempSellPrice,"9");
        require(!tempIsSell,"10");
        require(!tempIsCancel,"11");

        paintingInfo[_paintingId].buyAddr = msg.sender;

        uint256 tempCommissionPrice = paintingInfo[_paintingId].commPrice;
        uint256 tempTruePrice =  tempSellPrice - tempCommissionPrice;
        tempSellAddr.transfer(tempTruePrice);
        owner.transfer(tempCommissionPrice);
        paintingInfo[_paintingId].isSell = true;
    }
}
