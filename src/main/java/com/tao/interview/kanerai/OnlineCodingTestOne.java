package com.tao.interview.kanerai;

import java.util.List;

public class OnlineCodingTestOne {
    static class Column {
    }

    public void moveColumn(List<Column> table, int from_index, int to_index) {
        Column column = table.remove(from_index);
        table.add(to_index, column);
    }
}
