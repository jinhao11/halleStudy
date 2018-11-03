package com.halle.java.base.algorithm.stack;

import com.sun.xml.internal.ws.policy.spi.PolicyAssertionValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 对算术表达式进行计算，方法中，不对参数进行正确性校验
 */
public class ArithmeticExpressionParse {
    public static double computPostfixExpression(List<String> list){
        Stack<String> stack = new Stack();
        for (String s : list) {
            if( Symbol.NUM.equals( Tool.checkSymbol(s) ) ){
                stack.push(s);
            }
            //从栈中取出两个数字进行计算，计算值写回栈中
            double behind,first;
            if( Symbol.OPT.equals( Tool.checkSymbol(s) ) ){
                behind = Double.valueOf( stack.pop() );
                first =  Double.valueOf( stack.pop() );
                double tempResult = Tool.computing(first,behind,s);
                stack.push(String.valueOf( tempResult ));
            }
        }

        return Double.valueOf( stack.pop() );
    }





    public static List<String> convertToPostfixExpression(String[] input){
        List<String> postfixExpression = new ArrayList<>();
        java.util.Stack<String> stack = new Stack<>();
        for (String str : input) {
            Symbol s = Tool.checkSymbol(str);
            //当是数字时，直接输出
            if(s.equals(Symbol.NUM)){
                postfixExpression.add(str);
            }else if(s.equals(Symbol.BRACKET)){ //当是括号时
                /**
                 *  如果是（，入栈
                 *  如果是）则进入循环
                 *  loop：  弹出一项，不为（则输出
                 *          为（则退出逻辑循环
                 */
                if(str.equals("(")) {
                    stack.push(str);
                    continue;
                }else{
                    while( !stack.empty() ) {
                        String stackPop = stack.pop();
                        if(stackPop.equals("(")) {
                            break;
                        }else{
                            postfixExpression.add(stackPop);
                        }
                    }
                }


            }else{//当是运算符时
                /**当前运算符optcurr
                 * 栈空时，入栈
                 * 非空 loop:
                 *          弹出一项opttop：若为（推（入栈
                 *                   若为操作符且比optcurr级别小，opttop入栈，optcurr入栈 退出循环
                 *                   若大于等于optcurr输出opttop
                 * 当没有运算符optcurr之后，将栈的内容全部出栈输出
                 */
                if(stack.isEmpty()){
                    stack.push(str);
                }else{
                    while (true){
                        String stackPop = stack.pop();
                        if( stackPop.equals("(") ){
                            stack.push(stackPop);
                            stack.push(str);
                            break;
                        }else{
                            if( Tool.operatorCompare(stackPop,str) ) {
                                stack.push(stackPop);
                                stack.push(str);
                                break;
                            }else{
                                postfixExpression.add(stackPop);
                            }
                        }
                    }
                }
            }
        }


        while( !stack.isEmpty() ){
            postfixExpression.add(stack.pop());
        }

        return postfixExpression;
    }


    enum Symbol {
        OPT,//操作符
        BRACKET,//括号
        NUM;//数字
    }

    static class Tool{
        /**
         * 返回比较参数是否比被比较参数大,只包含加减乘除运算
         * @param comparedPara 被比较参数
         * @param comparePara 比较参数
         * @return true 比较参数比被比较参数大 false 比较参数比被比较参数小
         */
        public static boolean operatorCompare(String comparedPara,String comparePara){
            if( comparePara.equals("*") || comparePara.equals("/") ){
                if( comparedPara.equals("+") || comparedPara.equals("-") ){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }

        /**
         * 返回字符串是哪种类型
         * @param input
         * @return  返回
         */
        public static Symbol checkSymbol(String input){
            if(input.equals("*") || input.equals("/")
                    || input.equals("+") || input.equals("-")){
                return Symbol.OPT;
            }else if(input.equals("(") || input.equals(")") ){
                return Symbol.BRACKET;
            }else{
                return Symbol.NUM;
            }
        }


        public static double computing(double first,double second,String opt){
            switch (opt){
                case "+":return first+second;
                case "-":return first-second;
                case "*":return first*second;
                case "/":return first/second;
                default:return 0.0d;
            }
        }

    }
}
