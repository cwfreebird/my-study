package com.cw.heap;

/**
 * @author david.cai
 * @date 2018/6/26
 **/
public class HeapTest {
    private static final int _1M = 1024 * 1024;

    //-Xms20m -Xmx20m -Xmn10m -verbose:gc -XX:+PrintGCDetails -XX:+PrintTenuringDistribution -XX:+PrintGCTimeStamps
    public static void main(String[] args) throws InterruptedException {
        byte[] byte1 = new byte[2 * _1M];
        byte[] byte2 = new byte[2 * _1M];
        byte[] byte3 = new byte[2 * _1M];
        byte[] byte4 = new byte[2 * _1M];
        byte[] byte5 = new byte[2 * _1M];

        byte[] byte6 = new byte[5 * _1M];

        byte[] byte7 = new byte[2 * _1M];


    }
}
