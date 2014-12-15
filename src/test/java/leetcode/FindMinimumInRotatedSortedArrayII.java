package leetcode;

/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
 */

public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] num) {
        return findMinBinarySearch(num, 0, num.length - 1);
    }

    private int findMinBinarySearch(int[] num, int start, int end) {
        if (start == end) return num[start];
        int mid = (start + end) / 2;
        int left = findMinBinarySearch(num, start, mid);
        int right = findMinBinarySearch(num, mid + 1, end);
        return Math.min(left, right);
    }
}
