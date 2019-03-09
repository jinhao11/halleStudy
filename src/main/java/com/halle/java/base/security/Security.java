package com.halle.java.base.security;

import org.junit.Test;

public class Security {
    @Test
    public void testInt(){
        int a = 2147483600;
        int b = 50;
        int c = 2147483647;
        //注意 此处可能会发生越界
        if(a + b < c){
            System.out.println("a + b < c");
        }

        //应采用如下判断，但要主要负数的情况
        if(a < c - b){
            System.out.println("a < c – b");
        }
    }
}
