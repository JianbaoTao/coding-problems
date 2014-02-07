package leetcode;

/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordBreakII {
    Map<String, ArrayList<String>> cache = new HashMap<String, ArrayList<String>>();

    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        cache.clear();
        return wordBreakDFS( s, dict );
    }

    ArrayList<String> wordBreakDFS( String s, Set<String> dict ) {
        ArrayList<String> result = new ArrayList<String>(), tmpList;

        for( int i = 0; i < s.length(); i++ ) {
            String head = s.substring(0, i+1);
            String tail = s.substring(i+1);
            if( dict.contains(head) ) {
                if( tail.length() == 0 ) {
                    result.add( head );
                    break;
                }
                if( cache.containsKey( tail ) ) {
                    tmpList = cache.get( tail );
                } else {
                    tmpList = wordBreakDFS( tail, dict );
                }
                for( String ss : tmpList ) {
                    result.add( head + " " + ss );
                }
            }
        }

        cache.put( s, result );

        return result;
    }


}
