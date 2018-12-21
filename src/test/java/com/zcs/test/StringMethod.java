package com.zcs.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

@SpringBootTest
public class StringMethod {

    @Test
    public void testInner(){
        //在虚拟机初始化时已经将java这个字符串放进了常量池
        String str2 = new StringBuffer("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        String str1 = new StringBuffer("程序").append("科学").toString();
        System.out.println(str1.intern() == str1);


    }
}
