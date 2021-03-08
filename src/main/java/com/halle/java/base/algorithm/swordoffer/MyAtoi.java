package com.halle.java.base.algorithm.swordoffer;

import javax.security.sasl.AuthorizeCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 *
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 */
public class MyAtoi {
    public int strToInt(String str) {
        if(str == null || str.length()<=0) return Integer.MIN_VALUE;
        List<Character> num = new ArrayList<>(100);
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if(aChar == ' ') continue;
            if(num.isEmpty() && isIllegeChar(aChar)) return Integer.MIN_VALUE;
            if(!isIllegeChar(aChar)){
                num.add(aChar);
            }else{

            }
        }
        return Integer.MIN_VALUE;
    }

    private boolean isIllegeChar(char aChar){
        if (aChar<'0' || aChar>'9'){
            return true;
        }else{
            return false;
        }

    }

    private Integer convertCharsToInteger(List<Character> chars){
        return null;
    }

    public static void main(String[] args) {
    }
}
