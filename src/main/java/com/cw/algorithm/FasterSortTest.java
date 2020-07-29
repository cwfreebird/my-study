package com.cw.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 快速排序
 * @author david.cai
 * @date 2020/5/14
 */
@Slf4j
public class FasterSortTest {
    static void sort(int[] array, int low, int high){
        int start = low;
        int end = high;
        int key = array[start];

        while (end > start){
            while (end > start && array[end] >= key){
                end --;
            }

            if (start != end) {
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }

            while (end > start && array[start] <= key){
                start ++;
            }

            if (start != end) {
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }
        }

        array[start] = key;

        if (start > low){
            sort(array, low, start - 1);
        }

        if (end < high){
            sort(array, end + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] array = initIntegerArray(10);
        log.info("before sort :{}", array);

        sort(array, 0, array.length - 1);

        log.info("after sort :{}", array);
    }

    static int[] initIntegerArray(int size){
        //return new int[]{12, 9, 37, 2, 46, 26, 42, 31, 16, 0};
        int[] array = new int[size];
        Random random = new Random();
        for (int index = 0; index < size; index ++) {
            array[index] = random.nextInt(50);
        }

        return array;
    }
}
