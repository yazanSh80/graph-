package com.example.algor3;

import jdk.internal.util.ArraysSupport;

import java.util.*;

public class ArrayListt<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    private static final int DEFAULT_CAPACITY = 500;

    public ArrayListt() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    private int size;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    protected transient int modCount = 0;
    transient Object[] elementData; // non-private to simplify nested class access

    E elementData(int index) {
        return (E) elementData[index];
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    public int size() {
        return size;
    }

    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }

    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    // Implementing the set() method to modify elements at specific indices
    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

}
