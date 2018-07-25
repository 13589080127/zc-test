package com.zcs.test.design.Adapter;

import java.io.IOException;

public interface TargetFileIO {

    void readFromFile(String fileName) throws IOException;

    void writeToFile(String fileName) throws IOException;

    void setValue(String key,String value);

    String getValue(String key);

}
