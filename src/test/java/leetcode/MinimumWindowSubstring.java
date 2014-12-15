package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public String minWindow(String S, String T) {
        if (S == null || T == null || S.length() < T.length()) {
            return "";
        }

        // char count map of T
        Map<Character, Integer> countMap = buildCountMap(T);

        int found = 0; // number  of found characters in S against T

        Deque<Integer> positionQueue = new ArrayDeque<Integer>(); // stores position in S of found characters

        int start = 0, end = S.length() - 1;

        int minLength = S.length();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (!countMap.containsKey(c)) {
                continue;
            }

            positionQueue.add(i);
            countMap.put(c, countMap.get(c) - 1);
            if (countMap.get(c) >= 0) {
                found++;
            }

            char leftChar = S.charAt(positionQueue.peek());
            while (countMap.get(leftChar) < 0) {
                positionQueue.poll();
                countMap.put(leftChar, countMap.get(leftChar) + 1); // increase count when pos removed from queue
                leftChar = S.charAt(positionQueue.peek());
            }
            if (found == T.length()) {
                int len = positionQueue.peekLast() - positionQueue.peek() + 1;
                if (len < minLength) {
                    minLength = len;
                    start = positionQueue.peek();
                    end = positionQueue.peekLast();
                }

            }
        }

        if (found == T.length()) {
            return S.substring(start, end + 1);
        }

        return "";

    }

    private Map<Character, Integer> buildCountMap(String t) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : t.toCharArray()) {
            int last = map.get(c) == null ? 0 : map.get(c);
            map.put(c, last + 1);
        }
        return map;
    }

    @Test
    public void test2() {
        String S = "cabwefgewcwaefgcf";
        String T = "cae";
        assertEquals(minWindow(S, T), "cwae");
    }


    @Test
    public void test() {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        assertEquals(minWindow(S, T), "BANC");
    }
}
