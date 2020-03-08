package com.halle.java.base.algorithm.leecode;

public class NumberofStepstoReduceaNumbertoZero {
    public int numberOfSteps (int num) {
        /**
         * 1.判断基偶
         * 2.计数器记录次数
         */
        int count = 0;
        while (num != 0){
            if(num%2==0){
                num = num/2;
            }else{
                num = --num;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NumberofStepstoReduceaNumbertoZero().numberOfSteps(0));
        System.out.println(new NumberofStepstoReduceaNumbertoZero().numberOfSteps(14));
        System.out.println(new NumberofStepstoReduceaNumbertoZero().numberOfSteps(2));
    }
}
