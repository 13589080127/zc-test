package com.zcs.test;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.function.Function;

@SpringBootTest
public class StringUtilTest extends AbstractTestNGSpringContextTests{


    @Test
    public void stringAnyEmpty(){
        System.out.println(StringUtils.isAnyEmpty("",":","1"));
    }

    /**
     * 字符串截取
     */
    @Test
    public void truncate(){
        String a = "abcdefghijk";
        System.out.println(StringUtils.truncate(a,5));
    }

    @Test
    public void truncateByIndex(){
        String a = "abcdefghijk";
        System.out.println(StringUtils.truncate(a,0,2));
    }

    @Test
    public void trim(){
        String a = "   ab  c  ";
        System.out.println(StringUtils.strip(a));
    }

    @Test
    public void stripEnd(){
        String a = "1200.00";
        System.out.println(StringUtils.stripEnd(a,".0"));
    }
}
