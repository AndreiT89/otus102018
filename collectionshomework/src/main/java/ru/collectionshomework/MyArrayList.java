package ru.collectionshomework;

import java.util.*;
import java.util.function.Consumer;
import java.util.Arrays;

public class MyArrayList<E> implements List<E> {
    private int DEFAULT_CAPACITY = 10;
    private Object[] EMPTY_ARRAY = {};
    private Object[] data;
    private int size = 0;

    public MyArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        int oldSize = size;
        int newSize = ++size;
        if (oldSize == data.length) {
            newSize = newSize + 10;
            Object[] newData = new Object[newSize];
            for (int i = 0; i < size - 1; i++) {
                newData[i] = this.data[i];
            }
            newData[oldSize] = e;
            this.data = Arrays.copyOf(newData, size);
        } else {
            this.data[newSize -  1] = e;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] inbound = c.toArray();
        System.arraycopy(inbound, 0, this.data, this.data.length, inbound.length);
        return inbound.length != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) this.data[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E old = (E) this.data[index];
        this.data[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) this.data, 0, size, c);
    }

    private class ListItr implements ListIterator<E> {
        private int cursor;
        int lastRet = -1;

        public ListItr(int index) {
            cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return (E) MyArrayList.this.data[lastRet = i];
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }

            try {
                MyArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {

            }
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}