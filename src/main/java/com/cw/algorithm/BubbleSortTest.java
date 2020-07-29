package com.cw.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 冒泡排序
 * @author david.cai
 * @date 2020/5/13
 */
@Slf4j
public class BubbleSortTest {
    static void bubbleSort(int[] array, int times){
        int i, j;
        for (i = 0; i < times; i ++){
            for (j = 1; j < times - i; j ++){
                if (array[j] < array[j - 1]){
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int size = 20;
        int[] array = initIntegerArray(size);

        log.info("before sort:{}", array);

        bubbleSort(array, size - 1);

        log.info(" after sort:{}", array);
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
