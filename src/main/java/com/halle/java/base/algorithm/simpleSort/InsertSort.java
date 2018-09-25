package com.halle.java.base.algorithm.simpleSort;

import org.omg.CORBA.IMP_LIMIT;

public class InsertSort {
    public static int[] swap(int[] input){
        //忽略入参校验
        for (int outer = 1; outer < input.length; outer++) {
            //当前值比前一个值大，无需进行调整;
            if(input[outer] >= input[outer-1]){
                continue;
            }

            int min = outer;

            /**
             * 当前一个位置比后一个位置数据大或者min大于0时
             * 找到最近的当前值更小的值的位置，调整数组
             */
            while (min !=0 && input[outer] < input[min-1]){
                min--;
            }

            int temp = input[outer];

            for(int j=outer-1 ; j>=min ; j--){
                input[j+1] =  input[j];
            }
            input[min] = temp;

        }
        return input;
    }
}
