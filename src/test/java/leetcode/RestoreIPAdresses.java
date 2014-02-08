package leetcode;

/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestoreIPAdresses {
    Map<ArrayList<String>, ArrayList<String>> cache =
            new HashMap<ArrayList<String>, ArrayList<String>>();

    public ArrayList<String> restoreIpAddresses(String s) {
        cache.clear();
        return dfs( s, 4 );

    }

    private ArrayList<String> dfs(String s, int n) {
        ArrayList<String> result = new ArrayList<String>(), tmpList, key;
        if( n == 0 ) return result;

        for( int i = 0; i < s.length(); i++ ) {
            String head = s.substring(0, i+1);
            String tail = s.substring(i+1);
            key = new ArrayList<String>();
            key.add( tail );
            key.add( n - 1 + "" );

            if( isValid( head ) ) {
                if( tail.length() == 0 ) {
                    if( n == 1 ) result.add( head );
                    break;
                }

                if( cache.containsKey( key ) ) {
                    tmpList = cache.get( key );
                } else {
                    tmpList = dfs( tail, n - 1 );
                }

                for( String ss : tmpList ) {
                    result.add( head + "." + ss );
                }
            }
        }


        key = new ArrayList<String>();
        key.add( s );
        key.add( n + "" );
        cache.put( key, result );
        return result;
    }

    private boolean isValid(String head) {
        if( head.length() > 3 ) return false;

        int n = Integer.valueOf( head );
        int length = head.length();

        if( n >= 100 && n <= 255 ) return true;

        if( n >= 10 && n < 100 && length == 2 ) return true;
        if( n >= 0 && n <= 9 && length == 1 ) return true;

        return false;
    }

}
