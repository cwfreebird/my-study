package com.cw.algorithm.LC;

/**
 * @author david.cai
 * @date 2020/8/26
 */
public class RemoveElement26 {
    public int removeElement(int[] nums, int val) {
        if(nums == null){
            return 0;
        }

        int n = 0;
        for(int cur = 0; cur < nums.length; cur++){
            if(nums[cur] != val){
                nums[n] = nums[cur];
                n++;
            }
        }

        return n;
    }

    static class Test{
        public static void main(String[] arg){
            RemoveElement26 romanToInt = new RemoveElement26();
            int[] num = {1,1,2,3,4,4,5,4};
            int n = romanToInt.removeElement(num, 1);

            System.out.println(n);
            for (int i = 0; i < n; i++) {
                System.out.print(num[i] + ",");
            }
        }
    }
}
