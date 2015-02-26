package com.tao;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdhocTest {
    static class IntegerComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            if (a.equals(b)) return 0;
            List<String> list = Arrays.asList(a + b, b + a);
            Collections.sort(list);
            if (list.get(0).equals(a + b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    @Test
    public void test_StringSort() throws Exception {
        List<String> list = Arrays.asList("34", "32", "43", "4", "3", "3", "40");
        Collections.sort(list, new IntegerComparator());
        Collections.reverse(list);
        System.out.println("sorted list: " + list);
    }
}
