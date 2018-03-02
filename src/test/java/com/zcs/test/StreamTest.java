package com.zcs.test;

import com.zcs.test.model.BrandInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.*;
import java.util.stream.Collectors;


@SpringBootTest
public class StreamTest extends AbstractTestNGSpringContextTests {

    public static void main(String[] args) {
        List<BrandInfo> list = new ArrayList<BrandInfo>();
        for (int i = 0;i<5;i++){
            BrandInfo brandInfo = new BrandInfo();
            brandInfo.setBrandId("0000");
            brandInfo.setBrandName("测试品牌"+(i+1));
            brandInfo.setBrhId("0200014"+(i+1));
            brandInfo.setCount(i);
            list.add(brandInfo);
        }
        List<BrandInfo> newList = list.stream().filter(l->l.getBrandId().equals("0001")).collect(Collectors.toList());
        BrandInfo[] a=list.stream().toArray(BrandInfo[]::new );
        Arrays.stream(a).forEach(b-> System.out.println(b.getBrandName()));
        newList.forEach(lists-> System.out.println(lists.getBrandName()));
        newList = list.stream().filter(l->l.getBrandId().equals("0000")).sorted(Comparator.comparing(BrandInfo::getBrhId)).collect(Collectors.toList());
        newList.forEach(s-> System.out.println(s));
        List<String> firstList  = list.stream().filter(l->l.getBrandId().equals("0000")).sorted(Comparator.comparing(BrandInfo::getBrhId))
                .map(BrandInfo::getBrhId).collect(Collectors.toList());
        firstList.forEach(fl-> System.out.println(fl));
        int totalCount = list.stream().mapToInt(BrandInfo::getCount).sum();
        System.out.println(totalCount);
        list.forEach(System.out::println);

        List<String> listTh = Arrays.asList("1","2","3","4","5","6");
        listTh.parallelStream().forEach(System.out::println);
        listTh.parallelStream().forEachOrdered(System.out::println);

    }
}
