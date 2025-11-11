package StackQueueTree;


/**
 * QueueInterface.java - implementation of the Queue ADT
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface QueueInterface<T>
{
    /** Adds a new entry to the back of this queue
     *   @param newEntry - An object to be added
     */
    public void enqueue(T newEntry);
    
    /** Removes and returns the entry at the front of this queue
     *   @return The object at the front of the queue
     *   @throws NoSuchElementException if the queue is empty before operation
     */
    public T dequeue();
    
    /** Returns the entry at the front of this queue
     *   @return The object at the front of the queue
     *   @throws NoSuchElementException if the queue is empty before operation
     */
    public T getFront();
    
    /** Detects whether this queue is empty
     *   @returns TRUE if the queue is empty, FALSE otherwise
     */
    public boolean isEmpty();
    
    /** Removes all entries from this queue. */
    public void clear();
}
