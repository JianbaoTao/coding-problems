package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        StringBuilder builder = new StringBuilder();
        List<Integer> unused = new ArrayList<Integer>();
        int nBang = 1; // n!
        for (int i = 1; i <= n; i++) {
            unused.add(i);
            nBang *= i;
        }
        k--; // 1-based

        while (unused.size() > 0) {
            nBang /= unused.size();
            int index = k / nBang; // index into unused
            k %= nBang; // update k
            builder.append(unused.get(index));
            unused.remove(index);
        }

        return builder.toString();
    }

    @Test
    public void test() {
        int n, k;

        n = 3;
        k = 1;
        System.out.println("n = " + n + ", k = " + k + "; result = " + getPermutation(n, k));

        n = 3;
        k = 2;
        System.out.println("n = " + n + ", k = " + k + "; result = " + getPermutation(n, k));

        n = 3;
        k = 3;
        System.out.println("n = " + n + ", k = " + k + "; result = " + getPermutation(n, k));

        n = 3;
        k = 4;
        System.out.println("n = " + n + ", k = " + k + "; result = " + getPermutation(n, k));

        n = 3;
        k = 5;
        System.out.println("n = " + n + ", k = " + k + "; result = " + getPermutation(n, k));

        n = 3;
        k = 6;
        System.out.println("n = " + n + ", k = " + k + "; result = " + getPermutation(n, k));

    }

}
