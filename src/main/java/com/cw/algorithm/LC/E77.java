package com.cw.algorithm.LC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author david.cai
 * @date 2020/7/31
 */
public class E77 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k){
        if (n < 0 || k < 0){
            return result;
        }

        List<Integer> list = new ArrayList<>();
        process(1, n, k, list);
        return result;
    }

    private void process(int start, int n, int k, List<Integer> list) {
        if (list.size() == k){
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < n + 1; i++){
            list.add(i);
            process(i + 1, n, k, list);
            list.remove(list.size() - 1);
        }
    }

    public static class Test{
        public static void main(String[] args) {
            int max = 4;
            int length = 2;
            E77 e = new E77();
            List<List<Integer>> calculation = e.combine(max, length);
            System.out.println(calculation);
        }
    }
}
