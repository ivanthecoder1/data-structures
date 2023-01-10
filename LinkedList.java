
import java.util.NoSuchElementException;
import java.util.Iterator;

public class LinkedList<E> {
    // Data members
    private Node head, tail;
    int size;

    // Inner class Node (only used in the linkedlist class)
    private class Node {
        E value;
        Node next;

        Node(E initialValue) {
            value = initialValue;
            next = null;
        }
    }

    // Constructor O(1)
    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    // Adding an item to the list O(1)
    public boolean addFirst(E item) {
        Node newNode = new Node(item);
        if (head == null) { // empty list
            head = tail = newNode;
        } else { // not empty
            newNode.next = head;
            head = newNode;
        }
        size++;
        return true;
    }

    // O(1)
    public boolean addLast(E item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    // O(1)
    public boolean add(E item) {
        return addFirst(item);
    }

    // Removing an item from the list O(1)
    public boolean removeFirst() {
        if (head == null)
            throw new NoSuchElementException();
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return true;
    }

    // O(n)
    public boolean removeLast() {
        if (head == null)
            throw new NoSuchElementException();
        if (size == 1)
            return removeFirst();
        Node current = head;
        Node previous = null;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        size--;
        return true;
    }

    // toString() method O(n)
    public String toString() {
        String output = "[";
        Node node = head;
        while (node != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += "]";
        return output;
    }

    // clear, check if empty, and size of the list
    public void clear() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    // Implementing an iterator for the list
    // tracl where we are in the list
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;

        public boolean hasNext() {
            return (current != null);
        }

        public E next() {
            if (current == null)
                throw new NoSuchElementException();
            E value = current.value;
            current = current.next;
            return value;
        }
    }

}