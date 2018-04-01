package com.zcs.test.design.Adapter;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AdapterFileProperties extends Properties implements TargetFileIO {

    @Override
    public void readFromFile(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        load(fileInputStream);
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        store(fileWriter,"");
    }

    @Override
    public void setValue(String key, String value) {
        setProperty(key,value);
    }

    @Override
    public String getValue(String key) {
        String value= getProperty(key);
        System.out.println(value);
        return value;
    }
}
