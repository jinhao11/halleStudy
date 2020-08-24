package com.halle.java.base.algorithm.leecode.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * 假设有这么一个类：
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 示例 2：
 *
 * 输入：n = 5
 * 输出："0102030405"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZeroEvenOdd {
    private int n;
    public ZeroEvenOdd(int n) {
        this.n = n;
    }
    private Semaphore zeroSemaphore = new Semaphore(0);
    private Semaphore evenSemaphore = new Semaphore(0);
    private Semaphore oddSemaphore = new Semaphore(0);

    private AtomicInteger integer = new AtomicInteger(0);



    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(integer.intValue() <= n){
            if(integer.intValue() == 0){
                printNumber.accept(integer.intValue());
                integer.getAndIncrement();
                zeroSemaphore.release(1);
            }else{
                if(evenSemaphore.tryAcquire()
                        || oddSemaphore.tryAcquire()){
                    printNumber.accept(0);
                    zeroSemaphore.release(1);
                }
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(integer.intValue() <= n){
            if(integer.intValue()%2 != 0){
                zeroSemaphore.acquire(1);
                printNumber.accept(integer.intValue());
                integer.getAndIncrement();
                evenSemaphore.release(1);
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(integer.intValue() <= n){
            boolean isOdd = integer.intValue()!=0 && integer.intValue()%2==0;
            if(isOdd){
                zeroSemaphore.acquire(1);
                printNumber.accept(integer.intValue());
                integer.getAndIncrement();
                oddSemaphore.release(1);
            }
        }
    }

    public static void main(String[] args) {
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
