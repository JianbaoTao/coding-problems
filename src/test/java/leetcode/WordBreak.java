package leetcode;

/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordBreak {
    Map<String, Boolean> cache = new HashMap<String, Boolean>();

    public boolean wordBreak(String s, Set<String> dict) {
        cache.clear();

        return wordBreakDFS(s, dict);

    }

    boolean wordBreakDFS( String s, Set<String> dict ) {
        boolean result = false;

        for( int i = 0; i < s.length(); i++ ) {
            String head = s.substring(0, i + 1 );
            String tail = s.substring( i + 1 );
            if( dict.contains( head ) ) {
                if( tail.length() == 0 ) {
                    result = true;
                    break;
                }
                if( cache.containsKey( tail ) ) {
                    result = cache.get( tail );
                } else {
                    result = wordBreakDFS( tail, dict );
                }
            }
            if( result ) break;
        }

        cache.put( s, result );
        return result;
    }

}
