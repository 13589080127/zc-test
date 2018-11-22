package com.zcs.test.example;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

public class Test1 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        listNode.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        listNode4.next = listNode5;
        ListNode listNode6 = new ListNode(4);
        listNode5.next = listNode6;
        System.out.println(JSON.toJSONString(addTwoNumbers(listNode,listNode4)));

    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = null;
        ListNode temp2 = null;
        StringBuffer a = new StringBuffer(String.valueOf(l1.val));
        StringBuffer b = new StringBuffer(String.valueOf(l2.val));
        while((temp1=l1.next)!=null && (temp2=l2.next)!=null){
            a = new StringBuffer().append(temp1.val+a.toString());
            b = new StringBuffer().append(temp2.val+b.toString());
            l1 = temp1;
            l2 = temp2;
        }
        String one = a.reverse().toString();
        String two = b.reverse().toString();
        StringBuffer resultBuffer = new StringBuffer(String.valueOf(new BigDecimal(one).add(new BigDecimal(two))));
        String result = resultBuffer.reverse().toString();
        char[] resultChar = result.toCharArray();
        char chars = resultChar[0];
        ListNode dummyRoot = new ListNode(Integer.parseInt(String.valueOf(chars)));
        ListNode ptr = dummyRoot;
        for(int i =1;i<resultChar.length;i++){
            ptr.next = new ListNode(Integer.parseInt(String.valueOf(resultChar[i])));
            ptr = ptr.next;
        }
        return dummyRoot;
    }
    private static ListNode getNext(ListNode listNode){
        return listNode.next;
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}