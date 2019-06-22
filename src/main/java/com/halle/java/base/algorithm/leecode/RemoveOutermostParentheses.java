package com.halle.java.base.algorithm.leecode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class RemoveOutermostParentheses {
    class Solution {
       /* public String removeOuterParentheses(String S) {
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
        }*/

        /**
         * 思路：
         * 根据字符串的对称性，能够明确最外层括号的位置。并以此将一个字符串分成多组来处理
         * 输出去掉最外层括号后的字符串内容
         * 优点:时间复杂度基本上就是遍历字符数组的时间
         * 缺点:切分数组需要额外的空间存储
         * @param S
         * @return
         */
        public String removeOuterParentheses1(String S) {
            char[] chars = S.toCharArray();
            StringBuilder retStr = new StringBuilder();
            int left=0;
            int right=0;
            int strFirstIndex = 0;

            for (int i = 0; i < chars.length; i++) {
                if(chars[i] == '('){
                    left++;
                }else{
                    right++;
                }

                if (left == right
                        && (left != 0 && right != 0)
                       ) {
                    if( strFirstIndex != i-1){
                        retStr.append(S.substring(strFirstIndex+1,i));
                    }
                    left = 0;
                    right = 0;
                    strFirstIndex = i+1;
                }
            }
            return retStr.toString();
        }

        public String removeOuterParentheses1Plus(String S) {
            StringBuilder retStr = new StringBuilder();
            int left=0;
            int right=0;
            int strFirstIndex = 0;

            for (int i = 0; i < S.length(); i++) {
                if(S.charAt(i) == '('){
                    left++;
                }else{
                    right++;
                }

                if (left == right
                        && (left != 0 && right != 0)
                ) {
                    if( strFirstIndex != i-1){
                        retStr.append(S.substring(strFirstIndex+1,i));
                    }
                    left = 0;
                    right = 0;
                    strFirstIndex = i+1;
                }
            }
            return retStr.toString();
        }

        public String removeOuterParentheses(String S) {
            StringBuilder builder = new StringBuilder();
            int position = 0;
            while (position < S.length()) {
                int endOfCurrentOuter = locationOfOuterClosingBracket(S, position);
                if (endOfCurrentOuter -1 > position) {

                    builder.append(S.substring(position+1, endOfCurrentOuter-1));
                }
                position = endOfCurrentOuter;
            }
            return builder.toString();
        }

        private int locationOfOuterClosingBracket(String s, int position) {
            if (s.length() - position >=2 && s.charAt(position) == '(') {
                position += 1;
                int matchingInnerParenthesis = 0;
                while (matchingInnerParenthesis >= 0) {
                    if (s.charAt(position) == '(') {
                        matchingInnerParenthesis++;
                    } else {
                        matchingInnerParenthesis--;
                    }
                    position++;
                }

            }
            return position;
        }
    }

    @Test
    public void testRemoveOuterParentheses(){
        String testStr1 = "()()()()()((()))(()((()())()()))(()()()()()((()))(()((()())()())))";
        String testStr2 = "()(())";
        String testStr3 = "()";

        System.out.println(new Solution().removeOuterParentheses(testStr1));
    }
}
