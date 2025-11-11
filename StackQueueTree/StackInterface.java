package StackQueueTree;


/**
 * StackInterface.java - implementation of our Stack ADT
 */
public interface StackInterface<T>
{
    //push() - adds a new entry to the top of this stack
    //   @param newEntry - the object to be added to the stack
    public void push(T newEntry);
    
    //pop() - removes and returns the entry at the top of this stack
    //   @return the object at the top of the stack
    //   @throws EmptyStackException if stack is empty before the operation
    public T pop();
    
    //peek() - returns the entry at the top of this stack
    //   @return the object at the top of the stack
    //   @throws EmptyStackException if stack is empty before the operation
    public T peek();
    
    //isEmpty() - checks if Stack is empty
    //   @return TRUE if empty; FALSE otherwise
    public boolean isEmpty();
    
    //clear() - removes all entries from the stack
    public void clear();
    
}
