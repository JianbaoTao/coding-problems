package com.tao.interview.kanerai;

import java.util.Iterator;

public interface MyList<T> {
    boolean add(T item);
    T getNext();
    Iterator<T> iterator();
}
