package StackQueueTree;


/**
 * BinaryTreeInterface.java - interface for a Binary Tree ADT
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface BinaryTreeInterface<T> extends TreeInterface<T>, TreeIteratorInterface<T>
{
    //sets the data in the root of this binary tree
    //  @param rootData - the object that is the data in the root node
    public void setRootData(T rootData);
    
    
    //sets this binary tree to a new binary tree
    //  @param rootData - the object that is the data in the root node
    //  @param leftTree - the left subtree of the new tree
    //  @param rightTree - the right subtree of the new tree
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                    BinaryTreeInterface<T> rightTree);
}
