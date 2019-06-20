package com.halle.java.base.algorithm.leecode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 *
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 * Example 1:
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 *
 * Example 2:
 * Input: J = "z", S = "ZZ"
 * Output: 0
 */
public class JewelsAndStones {
    static class Solution {
        public int numJewelsInStones(String J, String S) {
            char[] jChars = J.toCharArray();
            char[] sChars = S.toCharArray();
            Map<Character,Integer> map = new HashMap<>();

            for (int i = 0; i < sChars.length; i++) {
                char c = sChars[i];
                Integer value = map.get(sChars[i]);
                if(value == null){
                    map.put(c,1);
                }else{
                    Integer cValue = map.get(c);
                    map.put(c,++cValue);
                }
            }
            int sum = 0;
            for (int i = 0; i < jChars.length; i++) {
                char j = jChars[i];
                Integer value = map.get(j);
                if(value != null){
                    sum=sum+value;
                }
            }


            return sum;
        }

        /**
         * 分析:
         * 该方法是性能最好的实现方式，与上面的方法比较。toCharArray实际上底层做了一次数组拷贝，浪费了时间和存储。
         * 同时该方法无额外的内存开支，占用空间较小。但实际的时间复杂度为j.length*i.length。而上面方法则是采用hash*j.length，
         * 但是，实际上，上面的方法涉及到多次获取hash值，所以实际上比该方法多了很多无形的时间上的开销
         * @param J
         * @param S
         * @return
         */
        public int numJewelsInStonesLess0ms(String J, String S) {
            int count = 0;
            for (int i = 0; i< S.length(); i++) {
                if (J.indexOf(S.charAt(i)) >= 0) {
                    count++;
                }
            }
            return count++;
        }
    }

    @Test
    public void test() {

        new Solution().numJewelsInStones("abcdefghijklmnopqrstuvwxyz","abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
    }
}
