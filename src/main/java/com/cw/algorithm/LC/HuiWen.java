package com.cw.algorithm.LC;

/**
 * 回文数
 * @author david.cai
 * @date 2020/8/18
 */
public class HuiWen {
    public boolean isHuiWen(int num){
        if (num < 0){
            return false;
        }

        if (num != 0 && num % 10 == 0){
            return false;
        }

        int res = 0;
        while (num > res){
            int pop = num % 10;
            num /= 10;

            res = res * 10 + pop;

        }
        return res == num || num == res / 10;
    }

    public boolean isPalindrome(int x) {
        //边界判断
        if (x < 0) return false;
        int div = 1;
        //
        while (x / div >= 10) div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    static class Test{
        public static void main(String[] args) {
            HuiWen h = new HuiWen();

            //System.out.println(h.isHuiWen(122222));
            System.out.println(h.isPalindrome(12321));
        }
    }
}
