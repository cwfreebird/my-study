package com.cw.algorithm.LC;

import java.util.*;

/**
 * 输入：
 * [1,2,4,4]
 * 输出：
 * [
 * [1],[1,2],[1,4],[1,4,4],[1,2,4],[1,2,4,4],
 * [2],[2,4],[2,4,4],
 * [4],[4,4],
 * ]
 * @author david.cai
 * @date 2020/8/14
 */
public class E90 {
    private List<List<Integer>> result = new LinkedList<>();
    public List<List<Integer>> solution(int[] arr){
        if (arr.length < 1){
            return result;
        }

        List<Integer> list = new ArrayList<>();
        process(0, arr, list);
        return result;
    }

    private void process(int start, int[] arr, List<Integer> list) {
        result.add(new ArrayList<>(list));

        if (start == arr.length){
            return;
        }

        for (int i = start; i < arr.length; i++){
            list.add(arr[i]);
            process(i + 1, arr, list);
            list.remove(list.size() - 1);
        }
    }

    static class Test{
        public static void main(String[] args) {
            int[] arr = new int[]{1,2,4};
            E90 e90 = new E90();
            System.out.println(e90.solution(arr));
        }
    }
}
