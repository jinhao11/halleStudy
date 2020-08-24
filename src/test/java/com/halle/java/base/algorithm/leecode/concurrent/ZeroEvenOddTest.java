package com.halle.java.base.algorithm.leecode.concurrent;


import org.junit.Test;

import java.util.function.IntConsumer;

public class ZeroEvenOddTest {
    @Test
    public void ZeroEvenOddTest(){
        final ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            zeroEvenOdd.zero(System.out::print);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
         ,"zeroThread").start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                            try{
                                zeroEvenOdd.even(System.out::print);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                    }
                }
                ,"evenThread").start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try{
                            zeroEvenOdd.odd(System.out::print);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                }
                ,"oddThread").start();
    }
}