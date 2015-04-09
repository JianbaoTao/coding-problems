package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
 */

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int sBackup = -1, pBackup = -1;
        int is = 0, ip = 0;
        // eat s and p from left to right
        while (is < s.length()) {
            if (p == null || p.length() == 0) return false;
            // handle * in p
            if (ip < p.length() && p.charAt(ip) == '*') {
                while (ip < p.length() && p.charAt(ip) == '*') ++ip;
                if (ip == p.length()) return true;
                sBackup = is; // save char in s matched by *
                pBackup = ip; // save char in p after *
            }
            if (ip < p.length() && (p.charAt(ip) == '?' || p.charAt(ip) == s.charAt(is))) {
                is++;
                ip++;
            } else {
                if (sBackup == -1) return false;
                is = ++sBackup;
                ip = pBackup;
            }
        }

        while (ip < p.length() && p.charAt(ip) == '*') ip++;
        return is == s.length() && ip == p.length();

    }

    @Test
    public void testLeetCodeExamples() throws Exception {
        assertFalse(isMatch("aa","a"));
        assertTrue(isMatch("aa","aa"));
        assertFalse(isMatch("aaa","aa"));
        assertTrue(isMatch("aa", "*"));
        assertTrue(isMatch("aa", "a*"));
        assertTrue(isMatch("ab", "?*"));
        assertFalse(isMatch("aab", "c*a*b"));
    }
}
