package com.cw.algorithm.LC;

/**
 * @author david.cai
 * @date 2020/9/17
 */
public class TouTiao {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{9,8,7,6,5,4,3,2,1};
        System.out.println(findMiddle(arr));

        arr = new Integer[]{3,4,5,6,7,8,9,1,2};
        System.out.println(findMiddle(arr));

        arr = new Integer[]{6,7,8,9,1,2,3,4,5};
        System.out.println(findMiddle(arr));

        arr = new Integer[]{1,2,3,4,5,6,7,8,9};
        System.out.println(findMiddle(arr));
    }

    public static int findMiddle(Integer[] arr){
        int middle = arr.length / 2;
        for (int i = 0; i < arr.length - 1; i++) {
            if(arr[i] > arr[i + 1]){
                if(i == 0){
                    return arr[middle];
                }

                int index = (i + middle + 1) % arr.length;
                return arr[index];
            }
        }

        return arr[middle];
    }
}
