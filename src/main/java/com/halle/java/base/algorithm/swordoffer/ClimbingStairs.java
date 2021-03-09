package com.halle.java.base.algorithm.swordoffer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 0:0
 * 1:1
 * 2:2
 * 3:3
 * 4:5   1级4次  1级2次2级1次 2级一次1级两次 1级1次2级1次1级1次 2级2次
 * f(0)=0 f(1)=1 f(2)=2 f(3)=f(1)+f(2) f(n)=f(n-1)+f(n-2);同Fibonacci
 */
public class ClimbingStairs {
    public int numWays(int n) {
        return 0;
    }
}
