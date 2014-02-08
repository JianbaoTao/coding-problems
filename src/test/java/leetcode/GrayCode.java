package leetcode;

/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrayCode {
    public ArrayList<Integer> grayCode(int n) {

        ArrayList<Integer> result = new ArrayList<Integer>();

        ArrayList<Integer> codes = init( n );

        result.add( 0 );
        Set<ArrayList<Integer>> cache = new HashSet<ArrayList<Integer>>();
        cache.add( codes );

        int i = n - 1;

        while( i >= 0 ) {
            i = n - 1;
            while( i >= 0 ) {
                ArrayList<Integer> newCodes = changeOneBit( codes, i );
                if( cache.contains( newCodes ) ) {
                    i--;
                } else {
                    codes = newCodes;
                    cache.add( codes );
                    result.add( codesToNumber( codes ) );
                    break;
                }
            }
        }

        return result;

    }

    private Integer codesToNumber(ArrayList<Integer> codes) {
        Integer sum = 0;
        Integer weight = 1;
        for( int i = codes.size() - 1; i >= 0; i-- ) {
            sum += codes.get(i) * weight;
            weight *= 2;
        }
        return sum;
    }

    private ArrayList<Integer> changeOneBit(ArrayList<Integer> codes, int i) {
        ArrayList<Integer> newCodes = (ArrayList<Integer>) codes.clone();
        newCodes.set( i, (newCodes.get(i) + 1) % 2 );
        return newCodes;
    }

    ArrayList<Integer> init( int n ) {
        ArrayList<Integer> codes = new ArrayList<Integer>();
        for( int i = 0; i < n; i++ ) codes.add( 0 );
        return codes;
    }

    @Test
    public void test() {
        ArrayList<Integer> actual = grayCode( 2 );

        System.out.println(actual);
    }

}
