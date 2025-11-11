package StackQueueTree;


/**
 * BinaryNode.java - implementation of a Node for a binary tree
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BinaryNode<T>
{
    //declare properties
    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;
    
    //constructors
    public BinaryNode()
    {
        this(null);
    }
    
    public BinaryNode(T theData)
    {
        this(theData, null, null);
    }
    
    public BinaryNode(T theData, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild)
    {
        data = theData;
        leftChild = newLeftChild;
        rightChild = newRightChild;
    }
    
    //getters
    public T getData()
    {
        return data;
    }
    
    public BinaryNode<T> getLeftChild()
    {
        return leftChild;
    }
    
    public BinaryNode<T> getRightChild()
    {
        return rightChild;
    }
    
    //setters
    public void setData(T newData)
    {
        data = newData;
    }
    
    public void setLeftChild(BinaryNode<T> newLeftChild)
    {
        leftChild = newLeftChild;
    }
    
    public void setRightChild(BinaryNode<T> newRightChild)
    {
        rightChild = newRightChild;
    }
    
    //query
    public boolean hasLeftChild()
    {
        return (leftChild != null);
    }
    
    public boolean hasRightChild()
    {
        return (rightChild != null);
    }
    
    public boolean isLeaf()
    {
        return (leftChild == null) && (rightChild == null);
    }
    
    //return a (deep) copy of this BinaryNode
    public BinaryNode<T> copy()
    {
        BinaryNode<T> newRoot = new BinaryNode<>(data);
        //if child exists, set child of new node as a copy of my child
        if (leftChild != null)
            newRoot.setLeftChild(leftChild.copy());
        if (rightChild != null)
            newRoot.setRightChild(rightChild.copy());
            
        return newRoot;
    }
    
    //returns the height of the tree rooted at this BinaryNode
    public int getHeight()
    {
        return getHeight(this);
    }
    
    //recursive method that actually counts the height
    public int getHeight(BinaryNode<T> node)
    {
        int height = 0;
        
        if (node != null)
            height = 1 + Math.max(getHeight(node.getLeftChild()),
                                  getHeight(node.getRightChild()));
                                  
        return height;
    }
    
    //recursive method to return the number of nodes in tree rooted at this BinaryNode
    public int getNumberOfNodes()
    {
        int leftNumber = 0;
        int rightNumber = 0;
        
        if (leftChild != null)
            leftNumber = leftChild.getNumberOfNodes();
            
        if (rightChild != null)
            rightNumber = rightChild.getNumberOfNodes();
            
        return 1 + leftNumber + rightNumber;
    }
}
