package com.cw.classloader;

/**
 * @author david.cai
 * @date 2019/4/28
 */
public class TestMain {
    public static void main(String[] args) throws Exception
    {
        TestClassLoader mcl = new TestClassLoader();
        Class<?> c1 = Class.forName("com.cw.classloader.Test", true, mcl);
        Object obj = c1.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());

        Test test = new Test();
        System.out.println(test);
        System.out.println(test.getClass().getClassLoader());
    }
}
