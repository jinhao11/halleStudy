package com.halle.java.base.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;


public class LambdaTest {
    @Test
    public void firstLambdaTest(){
        Runnable r = ()->{
            System.out.println("hello");
        };

        new Thread(r).start();
    }


    @Test
    public void runLambdaTest(){
        new Thread(()->{
            System.out.println("i am runnable");
        }).start();
    }

    @Test
    public void testMethodReference1(){
        TestQ test = str->System.out.println(str);
        test.test("nihao");
    }

    @Test
    public void testMethodReference2(){
        TestQ test = System.out::println; //该方式只能用于接口中只有一个抽象方法
        test.test("nihao");
    }


    interface TestQ{
        void test(String test);

        //void anotherTest(String test1,String test2);
    }

    @Test
    public void testMethodReference3(){
        Function<Long,Long> f = Math::abs;
        System.out.println( f.apply(-3l) );
    }

    @Test
    public void testMethodReference4(){
        BiPredicate<String, String> b = String::equals;
        System.out.println( b.test("abc", "abcd") );
    }


    @Test
    //若Lambda表达式的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，就可以使用这种方法，如
    public void testMethodReference5(){
        String[] list = new String[]{"A","B"};
        Arrays.sort(list,String::compareToIgnoreCase);
        System.out.println(list[0]+" "+list[1]);
    }

    @Test
    //若Lambda表达式的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，就可以使用这种方法，如
    public void testMethodReference6(){
        BiPredicate<String, String> b = String::equals;
        b.test("abc", "abcd");
    }



}
