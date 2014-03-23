package leetcode;

/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].

 */

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] A) {
        int MAX_REPEAT = 2;
        if( A.length <= MAX_REPEAT ) return A.length;

        int p = 0;
        int r = 1;

        for( int i = 1; i < A.length; i++ ) {
            if( A[i] != A[p] ) {
                A[++p] = A[i];
                r = 1;
            } else {
                if( r < MAX_REPEAT ) {
                    A[++p] = A[i];
                    r++;
                }
            }
        }
        return p + 1;
    }
}
