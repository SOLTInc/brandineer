package com.solt_inc.test;

public class TestJava {

    public static void main(String[] args) {
        Runnable runnable = () ->System.out.println("Hello world!");
        runnable.run();
    }
}