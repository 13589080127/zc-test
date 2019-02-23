package com.zcs.test;

import java.util.function.Function;

public class FunctionDemoTest {
    public static void modifyTheValue(int value, Function<Integer,Integer> function){
        int newValue = function.apply(value);
        System.out.println(newValue);
    }

    public static void main(String[] args) {
        int incr = 20;
        int myNumber = 10;

        modifyTheValue(myNumber,val->val+incr);


        int a = 1;
        int b = 2;

    }

    public static Function<Integer, Integer> compose(Function<Integer,Integer> function){
        return function.compose(function);
    }
}
