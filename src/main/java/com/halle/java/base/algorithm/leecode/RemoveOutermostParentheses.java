package com.halle.java.base.algorithm.leecode;

import org.junit.Test;

import java.util.Stack;

public class RemoveOutermostParentheses {
    class Solution {
        public String removeOuterParentheses(String S) {
            StringBuilder retStr = new StringBuilder();
            char[] chars = S.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < chars.length; i++) {
                if(chars[i] == '('){
                    stack.push(chars[i]);
                }else{
                    char pop = stack.pop();
                    if(stack.empty()){
                        continue;
                    }
                    retStr.append(pop);
                    retStr.append(")");
                }

            }
            return retStr.toString();
        }
    }

    @Test
    public void testRemoveOuterParentheses(){
        String testStr = "(()(()))";
        System.out.println(new Solution().removeOuterParentheses(testStr));
    }
}
