package com.internship;

import java.util.NoSuchElementException;

public class CustomLinkedList<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;


    public int size() {
        return size;
    }

    public void addFirst(T el) {
        checkNotNull(el);

        final Node<T> newNode = new Node<>(el, null, head);

        if (head == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    public void addLast(T el) {
        checkNotNull(el);

        final Node<T> newNode = new Node<>(el, tail, null);

        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void add(int index, T el) {
        checkNotNull(el);

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        if (index == size) {
            addLast(el);
        } else if (index == 0) {
            addFirst(el);
        } else {
            Node<T> current = getNode(index);
            Node<T> newNode = new Node<>(el, current.prev, current);

            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.item;
    }

    public T getLast() {
        if (tail == null)
            throw new NoSuchElementException("List is empty");
        return tail.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return getNode(index).item;
    }

    public T removeFirst() {
        if (head == null)
            throw new NoSuchElementException("List is empty");

        final T removedItem = head.item;
        final Node<T> next = head.next;

        head = next;
        if (next == null) {
            tail = null;
        } else {
            next.prev = null;
        }
        size--;
        return removedItem;
    }

    public T removeLast() {
        if (tail == null)
            throw new NoSuchElementException("List is empty");
        final T removedItem = tail.item;
        final Node<T> prev = tail.prev;

        tail = prev;
        if (prev == null) {
            head = null;
        } else {
            prev.next = null;
        }
        size--;
        return removedItem;
    }

    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        final Node<T> nodeToRemove = getNode(index);
        final T removedItem = nodeToRemove.item;

        final Node<T> next = nodeToRemove.next;
        final Node<T> prev = nodeToRemove.prev;

        prev.next = next;
        next.prev = prev;

        size--;
        return removedItem;

    }

    private void checkNotNull(T el) {
        if (el == null)
            throw new NullPointerException("Element cannot be null");
    }

    Node<T> getNode(int index) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private static class Node<E> {
        final E item;
        Node<E> next;
        Node<E> prev;

        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}

