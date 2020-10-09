package com.cw.algorithm;

/**
 * @author david.cai
 * @date 2020/8/19
 */
public class SearchTest {
    int index = -1;
    public int find(int[] arr, int target){
        int start = 0;
        int end = arr.length - 1;

        while(start <= end){
            int mid = (start + end) / 2;

            if(arr[mid] == target){
                index = mid;
                end = mid - 1;
            } else if(arr[mid] > target){
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return index;
    }

    static class Test {
        public static void main(String[] args) {
            int[] arr = new int[]{1,2,2,2,2,2,3};
            int target = 2;
            SearchTest s = new SearchTest();

            System.out.println(s.find(arr, target));
        }
    }
}
