package leetcode;

import org.junit.Test;

/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int ia = a.length() - 1;
        int ib = b.length() - 1;
        int carry = 0;
        while (ia >= 0 || ib >= 0) {
            int d1, d2;
            if (ia >= 0) {
                d1 = a.charAt(ia) - '0';
            } else {
                d1 = 0;
            }
            if (ib >= 0) {
                d2 = b.charAt(ib) - '0';
            } else {
                d2 = 0;
            }
            int sum = d1 + d2 + carry;
            int d = sum % 2;
            carry = sum / 2;
            builder.append(d);
            ia--;
            ib--;
        }
        if (carry > 0) {
            builder.append(carry);
        }
        return builder.reverse().toString();
    }

    @Test
    public void test() {
        String a, b;

        a = "11";
        b = "1";
        System.out.println(a + " + " + b + " = " + addBinary(a, b));
    }

}
