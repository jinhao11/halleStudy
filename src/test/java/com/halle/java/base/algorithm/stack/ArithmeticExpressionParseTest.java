package com.halle.java.base.algorithm.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ArithmeticExpressionParseTest {

    @Test
    public void ToolOperatorCompareTest(){
        Assert.assertTrue(ArithmeticExpressionParse.Tool.operatorCompare("-","*"));
        Assert.assertFalse(ArithmeticExpressionParse.Tool.operatorCompare("/","+"));
    }


    @Test
    public void ToolCheckSymbol(){
        Assert.assertTrue(ArithmeticExpressionParse.Symbol.OPT.equals(ArithmeticExpressionParse.Tool.checkSymbol("*")));
        Assert.assertTrue(ArithmeticExpressionParse.Symbol.NUM.equals((ArithmeticExpressionParse.Tool.checkSymbol("2"))));
        Assert.assertTrue(ArithmeticExpressionParse.Symbol.BRACKET.equals((ArithmeticExpressionParse.Tool.checkSymbol("("))));
        Assert.assertFalse(ArithmeticExpressionParse.Symbol.OPT.equals((ArithmeticExpressionParse.Tool.checkSymbol("("))));

    }

    @Test
    public void convertToPostfixExpressionSimpleCaseTest(){
        String[] input = {"1","+","2"};
        List<String> list = ArithmeticExpressionParse.convertToPostfixExpression(input);
        System.out.println(list);
    }

    @Test
    public void convertToPostfixExpressionComplexCaseTest(){
        String[] input = {"1","+","2","*","(","3","+","2",")"};
        List<String> list = ArithmeticExpressionParse.convertToPostfixExpression(input);
        System.out.println(list);

    }

    @Test
    public void computingExpress(){
        //1+2*（3+2）
        String[] input = {"1","+","2","*","(","3","+","2",")"};
        double restult = ArithmeticExpressionParse.computPostfixExpression( ArithmeticExpressionParse.convertToPostfixExpression(input));
        Assert.assertEquals(11,restult,0.000000000000001);

        //1+2*（3+2）
        String[] input2 = {"1","+","2","*","(","3","+","-1",")"};
        double restult1 = ArithmeticExpressionParse.computPostfixExpression( ArithmeticExpressionParse.convertToPostfixExpression(input2));
        Assert.assertEquals(5,restult1,0.000000000000001);
    }
}