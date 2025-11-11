package StackQueueTree;


/**
 * LinkedQueue.java - Linked list implementation of Queue ADT
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode;     //reference to the node containing first element
    private Node lastNode;      //reference to the node containing last element
    
    private class Node
    {
        private T data;
        private Node next;
        
        private Node(T theData)
        {
            data = theData;
            next = null;
        }
    }
    
    public LinkedQueue()
    {
        firstNode = null;
        lastNode = null;
    }
    
    /** Adds a new entry to the back of this queue
     *   @param newEntry - An object to be added
     */
    public void enqueue(T newEntry)
    {
        //create new Node
        Node newNode = new Node(newEntry);
        
        //(special case): queue initially empty
        if (isEmpty())
            firstNode = newNode;
        else
        //(regular case): connect lastNode's next to be new Node
            lastNode.next = newNode;
        
        //either way, reassign last Node to be the new Node
        lastNode = newNode;
    }
    
    /** Removes and returns the entry at the front of this queue
     *   @return The object at the front of the queue
     *   @throws NoSuchElementException if the queue is empty before operation
     */
    public T dequeue()
    {
        T frontData = getFront();       //can throw exception if queue empty
        
        //cut out the first node
        firstNode = firstNode.next;
        
        //did we remove the only node?
        if (firstNode == null)
            lastNode = null;
            
        return frontData;
    }
    
    /** Returns the entry at the front of this queue
     *   @return The object at the front of the queue
     *   @throws NoSuchElementException if the queue is empty before operation
     */
    public T getFront()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return firstNode.data;
    }
    
    /** Detects whether this queue is empty
     *   @returns TRUE if the queue is empty, FALSE otherwise
     */
    public boolean isEmpty()
    {
        return (firstNode == null) && (lastNode == null);
    }
    
    /** Removes all entries from this queue. */
    public void clear()
    {
        firstNode = null;
        lastNode = null;
    }
}
