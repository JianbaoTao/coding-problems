package leetcode;

/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ScrambleString {

    Map<List<String>, Boolean> cache = new HashMap<List<String>, Boolean>();

    public boolean isScramble(String s1, String s2) {
        cache.clear();
        return isScrambleCore(s1, s2);
    }

    private boolean isScrambleCore(String s1, String s2) {
        boolean result = false;
        List<String> key = new ArrayList<String>();
        key.add( s1 );
        key.add( s2 );
        if( cache.containsKey( key ) ) return cache.get( key );

        if( s1.equals(s2) ) {
            cache.put( key, true );
            return true;
        }

        if( s1.length() == 1 ) {
            cache.put( key, false );
            return false;
        }

        int n = s1.length();

        for( int i = 0; i < n - 1; i++ ) {
            // forward
            String s11 = s1.substring(0, i+1);
            String s12 = s1.substring(i+1);
            String s21 = s2.substring(0, i+1);
            String s22 = s2.substring( i + 1 );

            if( isScrambleCore(s11, s21 ) && isScrambleCore( s12, s22 ) ) {
                result = true;
                break;
            }

            // backward
            s21 = s2.substring(0, n - 1 - i );
            s22 = s2.substring(n - 1 - i );

            if( isScrambleCore(s11, s22 ) && isScrambleCore( s12, s21 ) ) {
                result = true;
                break;
            }
        }
        cache.put( key, result );
        return result;
    }

    @Test
    public void test() {
        String s1, s2;

        s1 = "great";
        s2 = "rgeat";
        assertTrue( isScramble(s1, s2));

        s1 = "great";
        s2 = "rgtae";
        assertTrue( isScramble(s1, s2));


    }

}
