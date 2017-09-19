/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deque;

import java.util.Iterator;

/**
 *
 * @author cim217
 */
public class DLLDeque<Type> implements DequeADT<Type> {

    private int count;
    private DLLNode<Type> head, tail;

    public DLLDeque() {
        count = 0;
        head = tail = null;
    }
    
    /**
     * Mutator: enqueue() Add an element to the end of the end (tail)
     *
     */
    @Override
    public void addLast(Type elem) {
        DLLNode<Type> node = new DLLNode<Type>(elem);
        if (isEmpty()) // no item waiting in the queue currently
        {
            head = tail = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            node.setNext(null);
            tail = node;
        }

        //createCircle(node);
        count++;
    }

    @Override
    public Type removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue -- cannot dequeue");
        }
        Type result = head.getElement();
        head = head.getNext();
        //head.setPrevious(null);
        count--;

        if (count == 0) // empty queue
        {
            tail = null;
        }

        return result;
    }

    @Override
    public Type getFirst() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue -- no front element");
        }

        Type result = head.getElement();
        return result;
    }
    
    /**
     * Mutator: enqueue() Add an element to the end of the front (head)
     *
     */
    @Override
    public void addFirst(Type elem) {
        DLLNode<Type> node = new DLLNode<Type>(elem);
        if (isEmpty()) // no item waiting in the queue currently
        {
            head = tail = node;
        } else {
            head.setPrevious(node);
            node.setNext(head);
            node.setPrevious(null);
            head = node;
        }

        //createCircle(node);
        count++;
    }

    @Override
    public Type removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty deque -- cannot dequeue");
        }
        Type result = tail.getElement();
        tail = tail.getPrevious();
        tail.setNext(null);
        count--;

        if (count == 0) // empty queue
        {
            head = null;
        }

        return result;
    }

    /**
     * Accessor: getLast 
     *
     * @return
     */
    @Override
    public Type getLast() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Empty deque -- no front element");
        }

        Type result = tail.getElement();
        return result;
    }

    /**
     * Accessor: isEmpty() indicates whether or not the queue has no elements
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    public DLLNode<Type> getTail() {
        return tail;
    }

    /**
     * Accessor size() reports the number of elements in the queue
     *
     * @return count of items in the deque
     */
    @Override
    public int size() {
        return count;
    }

    public DLLNode< Type> traverse(Type elem, DLLNode< Type> node) {

        if (node.getElement() == elem) {
            return node;
        } else {
            return traverse(elem, node.getNext());
        }
    }

    @Override
    public boolean contains(Object elem) {
        return false;//traverse(elem, head) == null;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<Type> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Accessor: toString() displays the contents of the queue: </br>
     * one element after the other from front to rear
     */
    public String toString() {
        String out = size() + "\n";
        DLLNode<Type> current = new DLLNode<Type>();
        current = head;
        if (current == null) {
            out += "\tEmpty deque\n";
        } else {
            for (int i = 0; i < count; i++) {
                out += current.getElement();
                out += "\n";
                current = current.getNext();
            }
        }
        return out;
    }

}
