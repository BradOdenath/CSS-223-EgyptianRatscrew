/*
 * To change this license header, 
 * choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deque;

import java.util.Iterator;

/**
 *
 * @author Brad Odenath
 * @param <Type>
 */
public interface DequeADT<Type> {

    public void addLast(Type elem);

    //public boolean offerLast(Type elem);

    public Type removeFirst();

    //public Type pollFirst();

    public Type getFirst();

    //public Type peekFirst();

    public void addFirst(Type elem);

    //public boolean offerFirst(Type elem);

    public Type removeLast();

    //public Type pollLast();

    public Type getLast();
    
    //public Type peekLast();
    
    public boolean contains(Object obj);
    
    public boolean remove(Object o);
    
    public Iterator<Type> iterator();
    
    public int size();
    
    public boolean isEmpty();
        
    /*
    removelast occurance
    remove first occurance
    descending iterator
    */
    
    public String toString();

}
