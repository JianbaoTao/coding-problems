package leetcode;

/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchInRotatedSortedArray {
    public int search(int[] A, int target) {
        return binarySearchRotated( A, target, 0, A.length - 1 );
    }

    int binarySearch( int[] A, int target, int start, int end ) {
        if( end < start ) return -1;
        int mid = (start + end) / 2;
        if( A[mid] == target ) return mid;
        if( target < A[start] || target > A[end] ) return -1;
        if( target < A[mid] ) {
            return binarySearch( A, target, start, mid - 1 );
        } else {
            return binarySearch( A, target, mid + 1, end );
        }
    }

    int binarySearchRotated( int[] A, int target, int start, int end ) {
        if( end < start ) return -1;
        int mid = (start + end) / 2;
        if( A[mid] == target ) return mid;
        if( A[start] <= A[mid] ) {
            if( target >= A[start] && target < A[mid] ) return binarySearch( A, target, start, mid - 1 );
            else return binarySearchRotated( A, target, mid + 1, end );
        } else {
            if( target > A[mid] && target <= A[end] ) return binarySearch( A, target, mid + 1, end );
            else return binarySearchRotated( A, target, start, mid - 1 );
        }
    }

    @Test
    public void test() {
        int[] A;

        A = new int[] {3, 1};
        assertEquals( 1, search(A, 1) );
    }

}
