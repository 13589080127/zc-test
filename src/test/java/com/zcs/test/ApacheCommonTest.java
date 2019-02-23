package com.zcs.test;

import org.apache.commons.lang3.Validate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ApacheCommonTest extends AbstractTestNGSpringContextTests {
    @Test
    public void validateBoolean(){
        Validate.isTrue(false,"param is not %s",false);
    }
    @Test
    public void validateObject(){
        Validate.notNull(null,"param is not %s",null);
    }
    @Test
    public void validateString(){
        Validate.notBlank("","param is not %s",null);
    }

    @Test
    public void validateMap(){
        Validate.notEmpty(new HashMap<>());
    }

    @Test
    public void validateArray(){
        Validate.notEmpty(new String[2]);
    }

    @Test
    public void validateElementIsNull(){
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("1");
        Validate.noNullElements(list);
    }

    @Test
    public void validateArrayElementIsNotNull(){
        String[] a = new String[2];
        Validate.noNullElements(a);
    }

    /**
     * IndexOutOfBoundsException if the index is invalid
     */
    @Test
    public void validateArrayIndexIsNull(){
        String[] a = new String[2];
        Validate.validIndex(a,20);
    }
}
