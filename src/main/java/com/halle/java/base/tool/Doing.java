package com.halle.java.base.tool;

public class Doing {
    /**
     * 模拟占用程序执行耗时
     * @param mill 毫秒值
     */
    public static void doSomethingSpend(int mill){
        long startTime = System.currentTimeMillis();

        while( System.currentTimeMillis()-startTime < mill ){

        }
    }


    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        doSomethingSpend(2000);
        System.out.println( (System.currentTimeMillis()-time)/1000 );
    }
}
