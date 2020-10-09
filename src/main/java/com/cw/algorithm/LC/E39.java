package com.cw.algorithm.LC;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author david.cai
 * @date 2020/7/29
 */
@Slf4j
public class E39 {
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combination(int[] candidates, int target){
        if (candidates == null || candidates.length == 0 || target < 0){
            return lists;
        }

        List<Integer> list = new ArrayList<>();
        process(0, candidates, target, list);
        return lists;
    }

    private void process(int start , int[] candidates, int target, List<Integer> list) {
        if (target < 0){
            return;
        }

        if (target == 0){
            lists.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < candidates.length; i ++){
                list.add(candidates[i]);
                int leaving = target - candidates[i];
                process(i, candidates, leaving, list);
                list.remove(list.size() - 1);
                if (leaving < 0){
                    break;
                }
            }
        }
    }

    public static class Test {
        public static void main(String[] args) {
            int[] candidates = new int[]{2,3,6,7};
            int target = 7;
            E39 solution = new E39();
            List<List<Integer>> combination = solution.combination(candidates, target);

            log.info("{}", combination);

            //listTest();
        }

        public static void listTest(){
            List<List<Integer>> t = new ArrayList<>();

            List<Integer> l = new ArrayList<>();
            IntStream.range(1, 10).forEach(i -> {
                l.add(i);
                if (i % 3 == 0){
                    t.add(new ArrayList<>(l));
                }
            });
            System.out.println(t);
        }
    }
}
