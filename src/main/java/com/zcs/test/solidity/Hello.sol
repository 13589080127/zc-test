pragma solidity ^0.4.21;

contract Coin{
    address public minter;

    mapping (address => uint ) public balance;
    //轻客户端可以通过事件针对变化做出高效的的反应
    event Sent(address from,address to,uint amount);

    //开始创建只能合约的构造函数
    constructor () public{
        minter = msg.sender;
    }
    //往一个账户里面打钱
    function mint(address receiver,uint amount) public {
        if(msg.sender != minter){
            return;
        }
        balance[receiver] += amount;
    }
    //往一个账户里面打钱
    function send(address receiver,uint amount) public {
        if(balance[msg.sender] < amount)
            return;
        balance[msg.sender] -= amount;
        balance[receiver] +=amount;
        // 记录此日志信息
        emit Sent(msg.sender,receiver,amount);
    }

}