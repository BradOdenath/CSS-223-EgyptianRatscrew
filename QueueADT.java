/*
 * CSC-223 CS2  QUEUE ADT Interface using Generics
 */

package deque;

/**
 * CODE from JF 3rd edition by Lewis, Pasquale, & Chase
 *
 * @author Anita Wright, Brad Odenath
 */
public interface QueueADT<Type> {
   /**
    * Mutator: enqueue -- adds an item to the queue (by definition, the element
    * is added to the "rear" of the queue (line) </br>
    */
   public void enqueue(Type elem);

   /**
    * Mutator: dequeue -- removes an item from the "front" of the queue </br>
    *
*/
   public Type dequeue();

   /**
    * Accessor: first() -- returns a copy of the "front" of the queue without
    * removing it from the queueu
    */
   public Type first();

   /**
    * Accessor: isEumpty() -- returns true/false indicator regarding whether or
    * not the queue is empty
    */
   public boolean isEmpty();

   /**
    * Accessor: size() -- returns the count of items in the queue
    *
    */
   public int size();

   /**
    * Accessor: toString()
    *
    */
   public String toString();
}
