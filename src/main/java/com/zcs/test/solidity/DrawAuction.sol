pragma solidity ^0.4.22;

contract Test {
    //创建合约者
    address owner;
    // 竞拍价
    uint public highestBid;
    //竞拍人
    address public highestBidder;
    //竞拍结束时间
    uint public endTime;
    //可以取回的之前的出价
    mapping(address => uint) pendingReturns;

    // 记录当前最高竞拍价格（盲拍时不用）
    event HighestBidIncreased(address bidder, uint amount);
    //记录竞拍已经结束
    event AuctionEnded(address winner, uint amount);
    //是否已经竞拍完成  拍卖结束后设为 true，将禁止所有的变更
    bool ended;
    constructor(uint activityTime) public{
        owner = msg.sender;
        endTime = now+activityTime;
    }

    //第一方法是 有人出价
    function bid() public payable{
        /*require(now<endTime,"竞拍时间已经结束");
        require(!ended,"竞拍已经结束");
        require(msg.value>highestBid,"已存在其他竞拍价高于您的竞拍价");*/
        if(highestBid >0){
            pendingReturns[highestBidder] += highestBid;
        }
        highestBid = msg.value;
        highestBidder = msg.sender;
        emit HighestBidIncreased(msg.sender, msg.value);
    }

    //用户当前价格已经被定下来，要求主要取回自己投入的钱
    function withdraw() public returns (bool){
        uint amount = pendingReturns[msg.sender];
        if(amount >0){
            pendingReturns[msg.sender] = 0;
            msg.sender.transfer(amount);
        }
        return true;
    }

    //结束了 完成竞拍
    function actionEnd() public{
   /*     require(!ended,"当前竞拍已经结束");
        require(now>endTime,"当前竞拍还没结束");*/
        ended = true;

        emit AuctionEnded(highestBidder,highestBid);
        owner.transfer(highestBid);
    }
}
