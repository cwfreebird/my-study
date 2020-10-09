package com.cw.algorithm.LC;

/**
 * @author david.cai
 * @date 2020/8/26
 */
public class RemoveElement27 {
    public int removeElement(int[] nums) {
        if(nums == null){
            return 0;
        }

        int n = 0;
        for(int cur = 1; cur < nums.length; cur++){
            if(nums[n] != nums[cur]){
                n ++;
                nums[n] = nums[cur];
            }
        }

        return n + 1;
    }

    static class Test{
        public static void main(String[] arg){
            RemoveElement27 romanToInt = new RemoveElement27();
            int[] num = {1,1,2,3,4,4,5,6};
            int n = romanToInt.removeElement(num);

            System.out.println(n);
            for (int i = 0; i < n; i++) {
                System.out.print(num[i] + ",");
            }
        }
    }
}
