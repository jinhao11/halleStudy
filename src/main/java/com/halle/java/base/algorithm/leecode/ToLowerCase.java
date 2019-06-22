package com.halle.java.base.algorithm.leecode;

import org.junit.Test;

/**
 * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
 *
 *
 *
 * Example 1:
 *
 * Input: "Hello"
 * Output: "hello"
 * Example 2:
 *
 * Input: "here"
 * Output: "here"
 * Example 3:
 *
 * Input: "LOVELY"
 * Output: "lovely"
 */
public class ToLowerCase {

    class Solution {
        public String toLowerCase(String str) {
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if(chars[i] <= 'Z' && chars[i] >= 'A'){
                    chars[i]=(char) ((int)chars[i] + 32) ;
                }
            }
            return new String(chars);
        }
    }

    @Test
    public void testToLowerCase(){
        String str = "aBCDef";
        new Solution().toLowerCase(str);
    }
}
