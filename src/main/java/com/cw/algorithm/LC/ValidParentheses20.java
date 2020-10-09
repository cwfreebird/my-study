package com.cw.algorithm.LC;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author david.cai
 * @date 2020/8/23
 */
public class ValidParentheses20 {
    public boolean isValid(String s) {
        if(s == null || s.length() % 2 == 1){
            return false;
        }

        Stack<Character> list = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '('){
                list.push(')');
            } else if (c == '['){
                list.push(']');
            } else if (c == '{'){
                list.push('}');
            } else if (list.isEmpty() || c != list.pop()){
                return false;
            }
        }

        return list.isEmpty();
    }
}
