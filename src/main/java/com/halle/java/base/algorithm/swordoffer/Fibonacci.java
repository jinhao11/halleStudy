package com.halle.java.base.algorithm.swordoffer;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 */
public class Fibonacci {

    public int fib(int n) {
       int a=0,b=1,sum;
        for (int i = 0; i < n; i++) {
            sum = (a+b)%1000000007;
            a=b;
            b=sum;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fib(44));
    }
}
