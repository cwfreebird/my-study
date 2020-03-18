package com.cw.function;

/**
 * @author david.cai
 * @date 2019/6/17
 */
public class FunctionMain {
    public static void main(String[] args) {

        System.out.println(sayHello(a -> a + " test"));
    }

    public static String sayHello(SayHello hello){
        return hello.say("hello ");
    }
}
