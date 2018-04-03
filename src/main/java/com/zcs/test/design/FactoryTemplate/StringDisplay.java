package com.zcs.test.design.FactoryTemplate;

public class StringDisplay extends AbstractDisplay {
    private String string;
    private int width;

    public StringDisplay(String s){
        this.string = s;
        this.width = s.getBytes().length;
    }
    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        System.out.println("|"+string+"|");
    }

    @Override
    public void close() {
        printLine();
    }
    private void printLine(){
        System.out.print("+");
        for(int i=0;i<width;i++){
            System.out.print("-");
        }
        System.out.println("+");
    }
}
