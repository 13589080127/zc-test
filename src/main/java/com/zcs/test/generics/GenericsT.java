package com.zcs.test.generics;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GenericsT<A extends String> {

    private List<A> list = new ArrayList<>();

    public GenericsT(){
    }

    public void add(A t){
        list.add(t);
    }
    public <K> K showKeyName(K container){
        return container;
    }
}
