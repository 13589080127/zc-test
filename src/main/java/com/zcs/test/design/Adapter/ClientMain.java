package com.zcs.test.design.Adapter;

public class ClientMain {

    public static void main(String[] args) {
        TargetFileIO fileIO = new AdapterFileProperties();
        try{
            fileIO.readFromFile("/home/file.txt");
            fileIO.getValue("year");
            fileIO.setValue("year","2004");
            fileIO.setValue("month","4");
            fileIO.setValue("day","21");
            fileIO.writeToFile("/home/newfile.txt");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
