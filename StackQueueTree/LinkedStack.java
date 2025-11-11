package StackQueueTree;


/**
 * LinkedStack.java - A linked-based implementation of the Stack ADT
 */
import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode;     

    private class Node
    {
        private T data;
        private Node next;

        private Node(T dataPortion)
        {
            data = dataPortion;
            next = null;
        }
    }

    //constructor
    public LinkedStack()
    {
        topNode = null;
    }
    
    //push() - adds a new entry to the top of this stack
    //   @param newEntry - the object to be added to the stack
    public void push(T newEntry)
    {
        //create a new node
        Node newNode = new Node(newEntry);

        //attach new node to the top node
        newNode.next = topNode;

        //reassign top node to be the new node
        topNode = newNode;
    }
    
    //pop() - removes and returns the entry at the top of this stack
    //   @return the object at the top of the stack
    //   @throws EmptyStackException if stack is empty before the operation
    public T pop()
    {
        T result = peek();          //could potentially throw EmptyStackException
        
        //Assert: topNode != null
        topNode = topNode.next;     //cut out 1st node 
        
        return result;
    }
    
    //peek() - returns the entry at the top of this stack
    //   @return the object at the top of the stack
    //   @throws EmptyStackException if stack is empty before the operation
    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException();
            
        else
            return topNode.data;
    }
    
    //isEmpty() - checks if Stack is empty
    //   @return TRUE if empty; FALSE otherwise
    public boolean isEmpty()
    {
        return (topNode == null);
    }
    
    //clear() - removes all entries from the stack
    public void clear()
    {
        topNode = null;
    }
}
