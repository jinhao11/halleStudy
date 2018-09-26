package com.halle.java.base.algorithm.stack;

import java.util.HashMap;
import java.util.Map;

/**
 *  使用栈实现分隔符配对判断
 */
public class StackExample {
    private static Map<String,String> hashMap;

    static{
        hashMap = new HashMap<>();
        hashMap.put("}","{");
        hashMap.put("]","[");
        hashMap.put(")","(");
    }
    public boolean check(String[] input){
        //不判断为空情况

        Stack stack = new Stack(input.length);

        for (int i = 0; i < input.length; i++) {
            String str = input[i];
            switch (str){
                case "{":
                case "[":
                case "(":
                stack.push(str);
                break;
            }
            String popStr;
            switch (str){
                case "}":
                case "]":
                case ")":
                    popStr = stack.pop();
                    if( !hashMap.get(str).equals(popStr) ){
                        return false;
                    }
                    break;

            }


        }
        return true;
    }

    public static void main(String[] args) {
        String[] strArray = {"{","(","[","]",")","}"};
        StackExample e = new StackExample();
        System.out.println(e.check(strArray));
        String[] strArray2 = {"{","(","[",")","}"};
        System.out.println(e.check(strArray2));

    }

}
