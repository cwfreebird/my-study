package com.cw.algorithm.LC;

import com.cw.algorithm.SearchTest;

/**
 * @author david.cai
 * @date 2020/8/26
 */
public class MySqrt {
    public int mySqrt(int x) {
        int l = 0, r = x, n = 0;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if((long)mid * mid > x) {
                r = mid - 1;
            } else {
                n = mid;
                l = mid + 1;
            }
        }

        return n;
    }

    static class Test {
        public static void main(String[] args) {
            MySqrt s = new MySqrt();

            System.out.println(s.mySqrt(17));
        }
    }
}
