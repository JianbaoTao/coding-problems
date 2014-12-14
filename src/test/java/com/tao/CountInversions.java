package com.tao;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted.
 * If array is already sorted then inversion count is 0.
 * If array is sorted in reverse order that inversion count is the maximum.
 * Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j
 *
 * Example:
 * The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
 */
public class CountInversions {
    int inversionCountNaive(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[i] ) {
                    count++;
                }
            }
        }
        return count;
    }

    int inversionCountMergeSort(int[] A) {
        AtomicInteger inversionCount = new AtomicInteger(0);
        mergeSort(A, 0, A.length - 1, inversionCount);
        return inversionCount.get();
    }

    private void mergeSort(int[] A, int start, int end, AtomicInteger inversionCount) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(A, start, mid, inversionCount);
            mergeSort(A, mid + 1, end, inversionCount);
            merge(A, start, mid, end, inversionCount);
        }
    }

    private void merge(int[] A, int start, int mid, int end, AtomicInteger inversionCount) {
        int nL = mid - start + 1;
        int nR = end - mid;
        int[] L = new int[nL + 1];
        int[] R = new int[nR + 1];
        L[nL] = Integer.MAX_VALUE;
        R[nR] = Integer.MAX_VALUE;

        System.arraycopy(A, start, L, 0, nL);
        System.arraycopy(A, mid + 1, R, 0, nR);

        int l = 0, r = 0;

        for (int i = start; i <= end; i++) {
            if (L[l] < R[r]) {
                A[i] = L[l];
                l++;
            } else {
                A[i] = R[r];
                r++;
                int count = inversionCount.get();
                count += nL - l;
                inversionCount.set(count);
            }
        }
    }

    @Test
    public void testNaive() {
        int[] A = new int[]{1, 2, 3};
        assertEquals(inversionCountNaive(A), 0);

        A = new int[]{1, 3, 2};
        assertEquals(inversionCountNaive(A), 1);

        A = new int[]{3, 1, 2};
        assertEquals(inversionCountNaive(A), 2);
    }

    @Test
    public void testMerge() {
        int[] A = new int[]{1, 2, 3};
        assertEquals(inversionCountMergeSort(A), 0);

        A = new int[]{1, 3, 2};
        assertEquals(inversionCountMergeSort(A), 1);

        A = new int[]{3, 1, 2};
        assertEquals(inversionCountMergeSort(A), 2);
    }

}
