package com.cw.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * 二分查找
 * @author david.cai
 * @date 2020/5/13
 */
@Slf4j
public class BiSearchTest {
    static Integer biSearch(int[] array, int target){
        int start = 0;
        int end = array.length - 1;
        int mid;
        while (start <= end){
            mid = (start + end) / 2;
            if (array[mid] == target){
                return mid;
            } else if (array[mid] < target){
                start = mid + 1;
            } else{
                end = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,5,7,12,43,64,75,222,234};
        int target =  222;
        log.info("==============index:{}", biSearch(array, target));
    }
}
