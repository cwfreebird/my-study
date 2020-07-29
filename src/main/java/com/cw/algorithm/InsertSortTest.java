package com.cw.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 插入排序
 * @author david.cai
 * @date 2020/5/13
 */
@Slf4j
public class InsertSortTest {
    static void sort(int[] array){
        for (int i = 1; i < array.length; i ++){
            int insert = array[i];
            // 上一个元素
            int index = i - 1;

            while (index >= 0 && array[index] > insert){
                array[index + 1] = array[index];
                index --;
            }

            array[index + 1] = insert;
        }
    }

    public static void main(String[] args) {
        int[] array = initIntegerArray(10);
        log.info("before sort :{}", array);

        sort(array);

        log.info("after sort :{}", array);
    }

    static int[] initIntegerArray(int size){
        int[] array = new int[size];
        Random random = new Random();
        for (int index = 0; index < size; index ++) {
            array[index] = random.nextInt(100);
        }

        return array;
    }
}
