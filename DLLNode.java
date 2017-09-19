

/**
 * CSC-323 Linked List Stack Node class * DUE DATE:
 * DATE SUBMITTED:
 * PROGRAMMED BY: A. Wright, Brad Odenath
 
 *
 */
package deque;

/**
 *
 * @author A. Wright
 * CLASS DESCRIPTION: A double linked node is a container for any class type
 * with two links (references) 1) to the next Node and 2) to the previous Node
 */
public class DLLNode<T> {
   private T element;
   private DLLNode<T> next;
   private DLLNode<T> previous;
   
   /**
    * C O N S T R U C T O R
    * default set node and next to null
    */
   public DLLNode() {
      element = null;
      next = null;
      previous = null;
   }

   /**
    * C O N S T R U C T O R
    * conversion set node to input element and and next to null
    */
   public DLLNode(T inElem) {
      element = inElem;
      next = null;
      previous = null;
   }

   /**
    * Accessor: getElement()
    *
    * @return this DLLNode
    */
   public T getElement() {
      return element;
   }

   /**
    * Accessor: getNext()
    *
    * @return reference to next DLLNode
    */

   public DLLNode<T> getNext() {
      return next;
   }
   
   public DLLNode< T > getPrevious()
   {
       return previous;
   }

   /**
    * Mutator: setElement(T inputElement) allow user/client to change the value
    * of the Node
    *
    */
   public void setNode(T inElem) {
      element = inElem;
   }

   /**
    * Mutator: setNext( DLLNode<T> ptr)
    *
    * @return reference to next DLLNode
    */

   public void setNext(DLLNode<T> nodePtr) {
      next = nodePtr;
   }
   
   public void setPrevious( DLLNode< T > nodePtr )
   {
       previous = nodePtr;
   }
}
