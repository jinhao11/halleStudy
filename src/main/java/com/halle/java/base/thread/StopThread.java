package com.halle.java.base.thread;

public class StopThread {
    public static final Object lock = new Object();
    public static volatile  boolean flag = true;




    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                        System.out.println("i am thread1 i get lock");
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }

            }
        },"thread1");


        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                    System.out.println("i am thread2 i get lock");
                }

            }
        },"thread2");



        thread1.start();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

        thread1.stop();
        System.out.println("thread1 is stoped");
    }
}
