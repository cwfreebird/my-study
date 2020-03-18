package com.cw.classloader;

import ch.qos.logback.classic.pattern.MessageConverter;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author david.cai
 * @date 2019/4/12
 */
public class MyClassLoader extends ClassLoader {

    private String path;

    private String name;

    public MyClassLoader(String path, String name) {
        this.path = path;
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            File file = new File("D:/others/Test.class");
            in = new FileInputStream(file);

            out = new ByteArrayOutputStream();

            byte[] b = new byte[1024];

            int read = 0;
            while ((read = in.read(b)) > -1){
                out.write(read);
            }
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        byte[] bytes = out.toByteArray();

        return super.defineClass(name, bytes, 0, bytes.length);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader my = new MyClassLoader("D:\\others\\", "myLoader");
        Class<?> clz = Class.forName("com.cw.classloader.Test", false, my);
        Object o = clz.newInstance();
        System.out.println("heapTest:" + o);
        System.out.println("classLoader:" + o.getClass().getClassLoader());

        Test test = new Test();
        System.out.println("heapTest:" + test);
        System.out.println("classLoader:" + test.getClass().getClassLoader());
        Test test1 = new Test();
        System.out.println("heapTest:" + test1);
        System.out.println("classLoader:" + test1.getClass().getClassLoader());
    }
}
