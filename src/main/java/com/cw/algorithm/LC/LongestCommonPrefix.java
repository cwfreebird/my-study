package com.cw.algorithm.LC;

import javax.validation.constraints.Max;

/**
 * @author david.cai
 * @date 2020/8/22
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }

        if(strs.length == 1){
            return strs[0];
        }

        int min = strs[0].length();
        for(int i = 1; i < strs.length; i++){
            min = Math.min(min, strs[i].length());
        }
        StringBuilder sb = new StringBuilder();
        boolean common = false;
        for(int i = 0; i < min; i++){
            char last = strs[0].charAt(i);

            for(int j = 1; j < strs.length; j++){
                char curr = strs[j].charAt(i);

                if(last != curr){
                    common = false;
                    break;
                }

                common = true;
            }

            if(!common){
                break;
            }
            sb.append(last);
        }

        return sb.toString();
    }
}
