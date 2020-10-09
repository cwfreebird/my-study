package com.cw.algorithm.LC;

/**
 * @author david.cai
 * @date 2020/8/20
 */
public class RomanToInt {
    public int romanToInt(String s) {
        int ret = 0;
        int l = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++){
            int r = getValue(s.charAt(i));

            if (l < r) {
                ret = ret - l;
            } else {
                ret = ret + l;
            }
            l = r;
        }
        ret += l;
        return ret;
    }

    public int getValue(char c){
        switch (c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:
                return 0;
        }
    }

    int sum = 0;
    public int romanToInt2(String s) {
        cal(s.toCharArray(), 0);

        return sum;
    }

    public void cal(char[] ch, int pos) {
        int cur = getValue(ch[pos]);
        if (pos == ch.length - 1){
            sum = sum + cur;
            return;
        }
        int next = getValue(ch[pos + 1]);

        if (cur < next) {
            sum += next - cur;
            pos = pos + 2;
        } else {
            sum += cur;
            pos = pos + 1;
        }
        if (pos > ch.length - 1){
            return;
        } else {
            cal(ch, pos);
        }
    }

    static class Test{
        public static void main(String[] arg){
            RomanToInt romanToInt = new RomanToInt();

            //System.out.println(romanToInt.romanToInt2("MCMXCIV"));
            System.out.println(romanToInt.romanToInt2("LVIII"));
            //System.out.println(romanToInt.romanToInt2("IX"));
        }
    }
}
