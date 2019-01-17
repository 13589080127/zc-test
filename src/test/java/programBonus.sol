pragma solidity ^0.4.23;
library SafeMath {
      function mul(uint256 a, uint256 b) internal pure returns (uint256) {
        if (a == 0) {
          return 0;
        }
        uint256 c = a * b;
        assert(c / a == b);
        return c;
      }
      function div(uint256 a, uint256 b) internal pure returns (uint256) {
        // assert(b > 0); // Solidity automatically throws when dividing by 0
        uint256 c = a / b;
        // assert(a == b * c + a % b); // There is no case in which this doesn't hold
        return c;
      }
      function sub(uint256 a, uint256 b) internal pure returns (uint256) {
        assert(b <= a);
        return a - b;
      }
      function add(uint256 a, uint256 b) internal pure returns (uint256) {
        uint256 c = a + b;
        assert(c >= a);
        return c;
      }
    }
contract ProgramBonus
{
    using SafeMath for uint256;
 struct itmap
  {
    mapping(address => IndexMember) data;
    KeyFlag[] keys;
    uint256 size;
  }
struct KeyFlag 
  { 
      address member; 
      bool removed;
      
  }
  ///keyIndex  start with 1
  struct IndexMember 
  { 
    uint256 keyIndex;     //1 is the first 
    uint256 score;
    uint256 bonus;
    bool  worked;
      
  }

    itmap teamMembers; 
    address public sponser;  ///program sponser
    address public manager;   ///who can add score
    address public firstParty;  ///whom the program  for 
    
    uint256  public programEnd;
    bool  public ended;
    

    //event ScoreAdded(address member, uint256 score);
    //event ProgramEnded(uint256 bal,uint256 totalScore);
  

    
    constructor (address _manager, address _firstParty, uint256 _program_span) 
    public
    payable          ///program bonus saved in this.balance
    {
        sponser=msg.sender;
        manager=_manager;
        firstParty=_firstParty;
        programEnd=now+_program_span;
        
    }
    
    
    
  ///return insert or update flag
  function addMember(address _member) 
  public  
  returns (bool )

  {
        if(ended || msg.sender !=manager)
        {
            return false;
        }
      
    uint256 keyIndex = teamMembers.data[_member].keyIndex;    //get keyIndex by key _member  and insert into data mapping
    

    
    
    if (keyIndex > 0)   //if old member 
    {
        if(teamMembers.keys[keyIndex - 1 ].removed == true)
        {
            teamMembers.keys[keyIndex - 1].removed = false;
        }
        else
            return false;
  
       
       // teamMembers.keys[keyIndex].removed = false;
 
    }
    else       //new member
    {
      keyIndex = teamMembers.keys.length++;    //first  0
      teamMembers.data[_member].keyIndex = keyIndex + 1;   //first 1
      teamMembers.data[_member].worked = true;
      teamMembers.keys[keyIndex].member = _member;
      teamMembers.keys[keyIndex].removed = false;
      teamMembers.size++;

    }
    
    return true;
  }
    
  
    function removeMember( address _member) 
    public
    returns (bool success)
    
  {

        
        if(ended || msg.sender !=manager)
        {
            return false;
        }
        
        
        
    uint256 keyIndex = teamMembers.data[_member].keyIndex;
    if (keyIndex == 0   || teamMembers.keys[keyIndex - 1].removed )       //if member is not exists
      return false;
    
       
    
    ///delete teamMembers.data[key];
    
    
    teamMembers.keys[keyIndex - 1].removed = true;   //exists, only change the flag in case of re-adding
    teamMembers.size --;
    
    return true;
  }
    


    
    
    ///manager add score
    function  addScore(address[] _members, uint256[] _scores)
    public
    returns (bool)
    {
    //    require( manager == msg.sender);
   //     require(_members.length == _scores.length);
    //    require(!ended);
        
        if(manager!=msg.sender  || _members.length!= _scores.length  || ended)
        {
            return false;
        }
        
        
        //if  member is not added
        for(uint256 j=0; j<_members.length;j++)
        {
            if(!teamMembers.data[_members[j]].worked)
                return false;
        }
                
        
        
        for(uint256 i=0; i<_members.length;i++)
        {
            teamMembers.data[_members[i]].score  += _scores[i];
            //emit ScoreAdded(_members[i],_scores[i]);
        }
        
        return true;
    }
    
    
    
    
    
    
    ///manager end the program, get the bonus 
    function endProgram()
    public
    returns (bool)
    {
        if(ended || msg.sender !=manager)
        {
            return false;
        }
        
       // require(!ended);
       // require(msg.sender==manager);
        
        uint totalScore=0;
        for(uint256 i=0;i<teamMembers.keys.length; i++)
        {
            totalScore += teamMembers.data[teamMembers.keys[i].member].score;
            
        }
        
        for(uint256 j=0;j<teamMembers.keys.length; j++)
        {
            
            uint256    m_score= teamMembers.data[teamMembers.keys[j].member].score; 
            teamMembers.data[teamMembers.keys[j].member].bonus  = m_score.mul(address(this).balance).div(totalScore); 
            
           

        } 
        
        ended = true;
        
        //emit ProgramEnded(address(this).balance,totalScore);
        return true;
    }
    
    ///member withdraw bonus
    function withdraw()
    payable
    public
    returns (bool)
    {
        //require(ended);
        if(!ended)
        {
            return false;
            
        }
        
        
        //require(teamMembers.data[msg.sender].bonus>=withDrawAmount);
        //require(address(this).balance >= withDrawAmount);
        
        //teamMembers.data[msg.sender].bonus -= withDrawAmount;
        
        msg.sender.transfer(teamMembers.data[msg.sender].bonus);
        return true;
    }
  
    function getBalance()
    public view
    returns (uint256 )
    {
        return address(this).balance;
        
    }

}