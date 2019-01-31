package com.java.test;

import com.java.manager.DataRequest;

public class test_request {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        DataRequest dr = new DataRequest();
        dr.dataRequest();
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
