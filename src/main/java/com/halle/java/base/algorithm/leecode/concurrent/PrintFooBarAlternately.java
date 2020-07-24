package com.halle.java.base.algorithm.leecode.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintFooBarAlternately {
    private int n;
    public PrintFooBarAlternately(int n) {
        this.n = n;
    }
    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);


    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }
}
