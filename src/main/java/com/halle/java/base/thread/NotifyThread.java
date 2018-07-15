package com.halle.java.base.thread;

public class NotifyThread {

    public static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1 is notified");

                }

            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2 is notified");

                }

            }
        },"t2");



        Thread t3 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t3 is notified");

                }

            }
        },"t3");



        try {
            t1.start();
            Thread.sleep(1000);
            t2.start();
            Thread.sleep(1000);
            t3.start();

            synchronized (lock){
                lock.notify();
                Thread.sleep(1000);

            }


            synchronized (lock){
                lock.notify();
                Thread.sleep(1000);

            }


            synchronized (lock){
                lock.notify();
                Thread.sleep(1000);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
