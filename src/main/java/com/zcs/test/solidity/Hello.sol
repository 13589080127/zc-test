pragma solidity ^0.4.21;

contract Coin{
    address public minter;

    mapping (address => unit ) public balance;
    //轻客户端可以通过事件针对变化做出高效的的反应
    event Sent(address from,address to,unit amount);

    //开始创建只能合约的构造函数
    constructor () public{
        minter = msg.sender;
    }
    //往一个账户里面打钱
    function mint(address receiver,unit amount) public {
        if(msg.sender != minter){
            return;
        }
        balance[receiver] += amount;
    }
    //往一个账户里面打钱
    function send(address receiver,unit amount) public {
        if(balance[msg.sender] < amount)
            return;
        balance[msg.sender] -= amount;
        balance[receiver] +=amount;
        //为了将交易数据同步到区块浏览器上面，所有需要使用event事件
        emit Sent(msg.sender,receiver,amount);
    }

}