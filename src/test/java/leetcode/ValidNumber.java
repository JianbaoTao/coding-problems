package leetcode;

import org.junit.Test;

/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */
public class ValidNumber {

    public boolean isNumber(String s) {
        return isNumber_01(s);
    }

    public boolean isNumber_01(String s) { // reference: https://github.com/leetcoders/LeetCode/blob/master/ValidNumber.h
        s = s.trim().toLowerCase();
        if (s.length() == 0) {
            return false;
        }

        boolean exp = false;
        boolean num = false; // indicate final state
        boolean dot = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'e') {
                if (exp || !num) {
                    return false;
                }
                exp = true;
                num = false;
            } else if (c >= '0' && c <= '9') {
                num = true;
            } else if (c == '.') {
                if (dot || exp) {
                    return false;
                }
                dot = true;
            } else if (c == '+' || c == '-') {
                if (i != 0 && (!exp || s.charAt(i - 1) != 'e')) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return num;
    }

    @Test
    public void test() {
        String s = ".1";
        System.out.println("is " + s + " a number? " + isNumber(s));

        s = "0e2";
        System.out.println("is " + s + " a number? " + isNumber(s));

    }
}
