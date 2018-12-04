pragma solidity ^0.4.0;

contract DrawAuction2 {
    //创建合约者
    address owner;
    // 竞拍价
    uint public highestBid;
    //竞拍人
    address public highestBidder;
    //竞拍结束时间
    uint public endTime;
    //可以取回的之前的出价
    //mapping(address => uint) pendingReturns;

    // 记录当前最高竞拍价格（盲拍时不用）
    event HighestBidIncreased(address bidder, uint amount);
    //记录竞拍已经结束
    event AuctionEnded(address winner, uint amount);
    //日志记录
    event log(string log,uint u);
    //是否已经竞拍完成  拍卖结束后设为 true，将禁止所有的变更
    bool ended;
    constructor(uint activityTime) public{
        owner = msg.sender;
        endTime = now+activityTime;
    }

    //第一方法是 有人出价
    function bid() public payable{
        emit log("当前时间",now);
        emit log("当前竞拍价格",msg.value);
        emit log("最高值",highestBid);
        //必须是在规定时间内
        require(now<endTime,"竞拍时间已经结束");
        //必须是还在竞拍中
        require(!ended,"竞拍已经结束");
        //判断出价是否大于最高价
        require(msg.value>highestBid,"已存在其他竞拍价高于您的竞拍价");
        uint tempBid = highestBid;
        //赋予最高价
        highestBid = msg.value;
        if(tempBid >0){
            //上一个最高人返回钱
            highestBidder.transfer(tempBid);
        }
        //最高者
        highestBidder = msg.sender;
        // 记录说当前最高价 和最大值是这个家伙
        emit HighestBidIncreased(msg.sender, msg.value);
    }
    //结束了 完成竞拍
    function actionEnd() public{
        //第一步检查条件
        emit log("结束时间",endTime);
        require(!ended,"当前竞拍已经结束");
        require(now>endTime,"当前竞拍还没结束");

        //改变状态
        ended = true;

        //记录一下 当前竞拍已经结束
        emit AuctionEnded(highestBidder,highestBid);

        //把钱转给收益人
        owner.transfer(highestBid);
    }

}
