package com.skillbox;

import java.util.Collection;

public class StackImpl<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final E[] elements;
    private int size;
    private int maxSize;

    public StackImpl() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public StackImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        elements = (E[]) new Object[capacity];
        size = 0;
        maxSize = capacity;
    }

    @Override
    public void push(E element) throws StackException {
        if (isFull()) {
            throw new StackException("Stack is full");
        }
        elements[size++] = element;
    }

    @Override
    public E pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack is empty");
        }
        E element = elements[--size];
        elements[size] = null;
        return element;
    }

    @Override
    public E peek() throws StackException {
        if (isEmpty()) {
            throw new StackException("Stack is empty");
        }
        return elements[size - 1];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == maxSize;
    }

    @Override
    public void pushAll(Collection<? extends E> src) throws StackException {
        for (E element : src) {
            push(element);
        }
    }

    @Override
    public void popAll(Collection<? super E> dst) throws StackException {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    @Override
    public void setMaxSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Max size must be positive");
        }
        if (size < this.size) {
            throw new IllegalArgumentException("Max size cannot be smaller than current size");
        }
        maxSize = size;
    }
}
