package StackQueueTree;


/**
 * TreeInterface.java - implementation of general tree ADT
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface TreeInterface<T>
{
    //get the value in the root node
    public T getRootData();
    
    //returns the height of this tree
    public int getHeight();
    
    //return the number of nodes in this tree
    public int getNumberOfNodes();
    
    //checks if the tree is empty
    public boolean isEmpty();
    
    //clears out the tree
    public void clear(); 
    
}
