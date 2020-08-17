package com.halle.java.base.algorithm.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s and an integer array indices of the same length.
 *
 * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 *
 * Return the shuffled string.
 */
public class ShuffleString1528 {
    public String restoreString(String s, int[] indices) {
        /**
         * 创建map维护indices值与下标的关系
         * 对indices进行排序
         * 根据map，重新创建转换后的char数组
         */
        if(s ==null || s.length() <=0
                || indices==null ||indices.length<=0) {
            return "";
        }
        char[] sChars = new char[indices.length];
        Map<Integer,Integer> map = new HashMap<Integer,Integer>(Double.valueOf(indices.length/0.75).intValue()+1);
        
        //对indices排序
        for (int i = 0; i < indices.length; i++){
            map.put(indices[i],i);
        }
        for (int i = 0; i < indices.length; i++) {
            for (int j = i; j < indices.length; j++) {
                if(indices[i] > indices[j]){
                    int temp = indices[i];
                    indices[i]=indices[j];
                    indices[j]=temp;
                }
            }
        }


        for (int i = 0; i < indices.length; i++) {
            sChars[i]=s.charAt(map.get(indices[i]));
        }
        return new String(sChars);

    }
}
