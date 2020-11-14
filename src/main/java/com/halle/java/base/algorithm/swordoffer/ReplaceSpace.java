package com.halle.java.base.algorithm.swordoffer;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 *
 * 作者：Krahets
 * 链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/50ywkd/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class ReplaceSpace {

    public String replaceSpace(String s) {
       /* String[] strs = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if(i+1 != strs.length){
                sb.append("%20");
            }
        }*/
       String replace = s;
       if( s!= null){
           replace = s.replace(" ","%20");
       }
       return replace;
    }

    public static void main(String[] args) {
        String test = "We are happy. ";
        ReplaceSpace replaceSpace = new ReplaceSpace();
        System.out.println(replaceSpace.replaceSpace(test));
    }
}
