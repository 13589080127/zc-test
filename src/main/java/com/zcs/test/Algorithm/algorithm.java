package com.zcs.test.Algorithm;

/**
 * 常规算法类
 *
 * @author dzm
 * @create 2018-02-26 18:16
 **/
public class algorithm {
    /**
     * 两个整数a,b，不使用+号的情况下，计算这两个数字的和
     *
     */
    private static int aplusb(int a,int b){
        if(a==0){
            return b;
        }
        if(b==0){
            return a;
        }
        int c,d;
        //对两个整数进行异或操作,然后做与操作，左移一位,递归直到没有进位为止
        c=a^b;
        d=(a&b)<<1;
        return aplusb(c,d);
    }

    /**
     * 设计一个算法，计算出n阶乘中尾部零的个数
     * 11! = 39916800，因此应该返回 2
     *
     */
    private static long trailingZeros(long n){
        //1.直接计算结果,然后转为字符串进行判断,结果是否为零的情况,在n值较大的时候,性能太差
//        long sum = 1;
//        for(int i = 1;i<=n;i++){
//            sum*=i;
//        }
//        String sumStr = String.valueOf(sum);
//        int count=1;
//        int index = sumStr.indexOf("0");
//        for(int i = index+1;i<sumStr.length();i++){
//            if(i+1<=sumStr.length()){
//            if(!sumStr.substring(i,i+1).equals("0")){
//                count=0;
//            }
//            count++;
//            }
//        }
//        return count;

        //2.加法操作，从5开始，每次进5，然后判断，但是效果达不到O(logN)
//        long count = 0;
//        long pwr = 25;
//        for(long temp=5;temp<=n;temp+=5){
//            //for循环内部的temp都是5的倍数，因此首先+1操作
//            count++;
//            pwr = 25;
//            // 判断是不是25、125、625...的倍数，并根据每次pwr的变化进行+1操作
//            while(temp%pwr == 0){
//                count++;
//                pwr*=5;
//            }
//        }
//        return count;
        //3.每次除5 http://blog.csdn.net/surp2011/article/details/51168272
        long count = 0;
        long temp = n/5;
        while(temp!=0){
            count+=temp;
            temp/=5;
        }
        return count;
    }

    public static void main(String[] args) {
        //两个整数a,b，不使用+号的情况下，计算这两个数字的和
//      System.out.println(aplusb(3,0));
        //设计一个算法，计算出n阶乘中尾部零的个数
//        System.out.println(trailingZeros(5555550000000L));


    }
}
