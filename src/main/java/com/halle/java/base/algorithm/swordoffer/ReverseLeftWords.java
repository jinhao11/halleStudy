package com.halle.java.base.algorithm.swordoffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

 */
public class ReverseLeftWords {

    public String reverseLeftWords(String s, int n) {
        if(s == null || s.length()<=0 || n<=0) {
            return s;
        }
        Queue<Character> queue = new LinkedList();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            queue.add(chars[i]);
        }
        for (int i = 0; i < n; i++) {
            //队尾增加数据
            char element = queue.remove();
            queue.add(element);
        }
        Object[] retChars =  queue.toArray();
        StringBuilder stringBuilder = new StringBuilder(s.length());
        for (Object retChar : retChars) {
            stringBuilder.append((char)retChar);
        }
        return stringBuilder.toString();
    }

    //更优秀的解法，substring底层采用System.copyArray，性能更好
    //https://segmentfault.com/a/1190000009922279
    public String reverseLeftWords2(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    public static void main(String[] args) {
        System.out.println(new ReverseLeftWords().reverseLeftWords("abcdefg",3));

    }
}
