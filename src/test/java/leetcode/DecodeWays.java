package leetcode;

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.
 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 The number of ways decoding "12" is 2.
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int f0 = 1, f1 = 1;
        for (int i = 1; i < s.length(); i++) {
            int d1 = s.charAt(i) - '0';
            int d0 = s.charAt(i - 1) - '0';
            // in normal case, f1 = f1 + f0;
            if (d1 == 0) f1 = 0; // only f0 contributes
            if (d0 * 10 + d1 < 10 || d0 * 10 + d1 > 26) f0 = 0; // only f1 contributes
            int tmp = f1;
            f1 = f1 + f0;  // normal case
            f0 = tmp;
        }
        return f1;
    }
}
