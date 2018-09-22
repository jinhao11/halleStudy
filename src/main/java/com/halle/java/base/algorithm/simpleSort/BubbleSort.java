package com.halle.java.base.algorithm.simpleSort;

/**
 * 实现冒泡排序
 */
public class BubbleSort {
    public static int[] swap(int[] input){
        //忽略入参校验
        for (int i = 0; i < input.length-1; i++) {
            for (int i1 = 0; i1 < input.length-1 - i; i1++) {
                int temp;
                //如果左边数字比右边大，交换
                if(input[i1] > input[i1+1] ) {
                    temp = input[i1+1];
                    input[i1+1] = input[i1];
                    input[i1] = temp;
                }
            }

        }
        return input;
    }




}
