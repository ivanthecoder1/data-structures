import java.util.LinkedList;

public class Queue<E> {
    private LinkedList<E> list; // use linkedlist to implement a queue

    public Queue() {
        list = new LinkedList<>(); 
    }

    public void offer(E item) {
        list.addLast(item); // back of the queue is the tail of the linkedlist
    }

    public E poll() {
        E value = list.getFirst(); // get element at the head of the list, front of queue is head of list
        list.removeFirst(); 
        return value;
    }

    public E peek() {
        return list.getFirst();
    }

    public String toString() {
        return "Queue: " + list.toString();
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}