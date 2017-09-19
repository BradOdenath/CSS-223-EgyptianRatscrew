/**
 * CSC-223 Queue Tester * DUE DATE: * DATE SUBMITTED: 
 * PROGRAMMED BY: A. Wright, Brad Odenath
 *
 *
 */
package deque;
//import java.util.Exc

import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author A. Wright CLASS DESCRIPTION:
 */
public class DLLCircularQueue<Type> implements QueueADT<Type> {

    private int count;
    private DLLNode<Type> head, tail;

    /**
     * C O N S T R U C T O R
     */
    public DLLCircularQueue() {
        count = 0;
        head = tail = null;
    }

    /**
     * Mutator: enqueue() Add an element to the end of the queue (tail)
     *
     */
    public void enqueue(Type elem) {
        DLLNode<Type> node = new DLLNode<Type>(elem);
        if (isEmpty()) // no item waiting in the queue currently
        {
            head = tail = node;
        } else {
            tail.setNext(node);
        }
        createCircle(node);

        count++;
    }

    public void createCircle(DLLNode< Type> node) {
        node.setPrevious(tail);
        node.setNext(head);
        tail = node;
        head.setPrevious(tail);
    }

    public DLLNode< Type> traverse(Type elem, DLLNode< Type> node) {

        if (node.getElement() == elem) {
            return node;
        } else {
            return traverse(elem, node.getNext());
        }
    }

    public DLLNode< Type> remove(Type elem) {
        DLLNode< Type> dump = traverse(elem, getHead());

        

        if (dump.equals(head)) {
            head = dump.getNext();
        }
        if (dump.equals(tail))
        {
            tail = dump.getPrevious();
        }
        
        dump.getPrevious().setNext(dump.getNext());
        dump.getNext().setPrevious(dump.getPrevious());

        //dump.setPrevious(null);
        //dump.setNext(null);

        count--;

        return dump;
    }

    /**
     * Mutator: dequeue() Removes the first item from the head of the queue
     */
    public Type dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue -- cannot dequeue");
        }
        Type result = head.getElement();
        head = head.getNext();
        head.setPrevious(tail);
        count--;

        if (count == 0) // empty queue
        {
            head = tail = null;
        }

        return result;
    }

    /**
     * Accessor first() -- returns a copy of the item at the front of the queue;
     * no remove
     *
     * @return copy of the element
     */
    public Type first() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue -- no front element");
        }

        Type result = head.getElement();
        return result;
    }

    public DLLNode< Type> getRandom() {
        DLLNode result = getHead();

        for (int i = 0; i < (new Random().nextInt(count) + 1); i++) {
            result = result.getNext();
        }

        return result;

    }

    public DLLNode< Type> getHead() {
        return head;
    }

    /**
     * Accessor: isEmpty() indicates whether or not the queue has no elements
     */
    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Accessor size() reports the number of elements in the queue
     *
     * @return count of items in the queue
     */
    public int size() {
        return count;
    }

    /**
     * Accessor: toString() displays the contents of the queue: </br>
     * one element after the other from front to rear
     */
    public String toString() {
        String out = "";
        DLLNode<Type> current = new DLLNode<Type>();
        current = head;
        if (current == null) {
            out += "Empty queue\n";
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
