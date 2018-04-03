package com.zcs.test.design.FactoryTemplate;

public class CharDisplay extends AbstractDisplay {
    public char aChar;
    public CharDisplay(char c){
        aChar = c;
    }
    @Override
    public void open() {
        System.out.print("<<");
    }

    @Override
    public void print() {
        System.out.print(aChar);
    }

    @Override
    public void close() {
        System.out.println(">>");
    }
}
