package StackQueueTree;

/**
 * BinaryTree.java - implementation of a binary tree
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.NoSuchElementException;
import java.util.Iterator;

public class BinaryTree<T> implements BinaryTreeInterface<T>
{
    private BinaryNode<T> root;

    //constructors
    public BinaryTree()
    {
        root = null;
    }

    public BinaryTree(T rootData)
    {
        root = new BinaryNode(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        initializeTree(rootData, leftTree, rightTree);
    }

    /* BinaryTreeInterface Methods */

    //sets the data in the root of this binary tree
    //  @param rootData - the object that is the data in the root node
    public void setRootData(T rootData)
    {
        root.setData(rootData);
    }

    //sets this binary tree to a new binary tree
    //  @param rootData - the object that is the data in the root node
    //  @param leftTree - the left subtree of the new tree
    //  @param rightTree - the right subtree of the new tree
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
    BinaryTreeInterface<T> rightTree)
    {
        initializeTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
    }

    //helper method to set the subtrees
    private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        //create a new root node with our root data
        root = new BinaryNode<>(rootData);

        //left subtree exists and is not empty, so attach it to the root
        if ((leftTree != null) && (!leftTree.isEmpty()))
            root.setLeftChild(leftTree.root);

        //right subtree exists and is not empty...
        if ((rightTree != null) && (!rightTree.isEmpty()))
        {
            //...and is distinct from left tree, so safe to attach
            if (rightTree != leftTree)
                root.setRightChild(rightTree.root);
            //otherwise both are the same, so make a copy
            else
                root.setRightChild(rightTree.root.copy());
        }

        //we can safely clear out leftTree
        if ((leftTree != null) && (leftTree != this))
            leftTree.clear();

        //we can safely clear out rightTree
        if ((rightTree != null) && (rightTree != this))
            rightTree.clear();
    }

    //setRootNode - sets the node of this binary tree
    public void setRootNode(BinaryNode<T> rootNode)
    {
        root = rootNode;
    }
    
    //getRootNode - gets reference to the root of this binary tree
    public BinaryNode<T> getRootNode()
    {
        return root;
    }
    
    /* TreeInterface Methods */

    //get the value in the root node
    public T getRootData()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        else
            return root.getData();
    }

    //returns the height of this tree
    public int getHeight()
    {
        int height = 0;
        if (root != null)
            height = root.getHeight();
        return height;
    }

    //return the number of nodes in this tree
    public int getNumberOfNodes()
    {
        int numNodes = 0;
        if (root != null)
            numNodes = root.getNumberOfNodes();

        return numNodes;
    }

    //checks if the tree is empty
    public boolean isEmpty()
    {
        return (root == null);
    }

    //clears out the tree
    public void clear()
    {
        root = null;
    }

    /* TreeIteratorInterface methods */

    public Iterator<T> getPreorderIterator()
    {
        return new PreorderIterator();    
    }
    public Iterator<T> getInorderIterator()
    {
        return new InorderIterator();
    }

    public Iterator<T> getPostorderIterator()
    {
        return new PostorderIterator();
    }

    public Iterator<T> getLevelOrderIterator()
    {
        return new LevelOrderIterator();
    }

    private class InorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;
        
        public InorderIterator()
        {
            nodeStack = new LinkedStack();
            currentNode = root;
        }
        
        //keep going as long as current Node is not null and stack is not empty
        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || currentNode != null;
        }
        
        //push node onto stack when we defer
        //pop when we visit and go right
        public T next()
        {
            BinaryNode<T> nextNode = null;
            
            //keep going left until we can't go left anymore
            while (currentNode != null)
            {
                nodeStack.push(currentNode);    //push current node to save its place
                currentNode = currentNode.getLeftChild();
            }
            
            //grab this leftmost node, save the data, and go right
            if (!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop(); //this is the node we are visiting
                
                currentNode = nextNode.getRightChild();
            } else
                throw new NoSuchElementException();
                
            return nextNode.getData();
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
    }
    
    private class PreorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        
        public PreorderIterator()
        {
            nodeStack = new LinkedStack();
            if (root != null)
                nodeStack.push(root);
        }
        
        //keep going as long as our stack is not empty
        public boolean hasNext()
        {
            return !nodeStack.isEmpty();
        }
        
        //pop immediately; then push right child, then left child onto stack
        public T next()
        {
            BinaryNode<T> nextNode = null;
            
            if (hasNext())
            {
                nextNode = nodeStack.pop();
                BinaryNode<T> leftChild = nextNode.getLeftChild();
                BinaryNode<T> rightChild = nextNode.getRightChild();
                
                //if child exists, push right then left
                if (rightChild != null)
                    nodeStack.push(rightChild);
                if (leftChild != null)
                    nodeStack.push(leftChild);
            } else
                throw new NoSuchElementException();
                
            return nextNode.getData();
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
    
    private class PostorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;
        
        public PostorderIterator()
        {
            nodeStack = new LinkedStack();
            currentNode = root;
        }
        
        //keep going as long as stack is not empty or the current node is not null
        public boolean hasNext()
        {
            return currentNode != null || !nodeStack.isEmpty();
        }
        
        //keep going left; if dead end, go right; pushing nodes as we defer onto the stack
        public T next()
        {
            BinaryNode<T> nextNode = null;
            BinaryNode<T> leftChild = null;
            BinaryNode<T> rightChild = null;
            
            //find the leftmost node with no left child
            while (currentNode != null)
            {
                nodeStack.push(currentNode);    //push current node as we defer it
                leftChild = currentNode.getLeftChild();
                
                //if we can't go left, then go right
                if (leftChild == null)
                    currentNode = currentNode.getRightChild();
                //otherwise keep going left
                else
                    currentNode = leftChild;
            }
            
            //we found the node
            if (!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop(); //pop it off the stack
                
                BinaryNode<T> parent = null;
                if (!nodeStack.isEmpty())
                {
                    parent = nodeStack.peek();
                    //did we get here via the parent's left?
                    if (nextNode == parent.getLeftChild())
                        currentNode = parent.getRightChild();
                    //otherwise, we are done with parent
                    else
                        currentNode = null;
                } else
                    currentNode = null; //no parent, so we just set to null
            } else
                throw new NoSuchElementException();
                
            return nextNode.getData();
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
    }
    
    private class LevelOrderIterator implements Iterator<T>
    {
        private QueueInterface<BinaryNode<T>> nodeQueue;
        
        public LevelOrderIterator()
        {
            nodeQueue = new LinkedQueue();
            //start with the root
            if (root != null)
                nodeQueue.enqueue(root);
        }
        
        // keep going as long as the queue is not empty
        public boolean hasNext()
        {
            return !nodeQueue.isEmpty();
        }
        
        //Dequeue the front node for processing; enqueue its children (to theback of the queue)
        public T next()
        {
            BinaryNode<T> currentNode = null;
            
            if (hasNext())
            {
                currentNode = nodeQueue.dequeue();
                BinaryNode<T> leftChild = currentNode.getLeftChild();
                BinaryNode<T> rightChild = currentNode.getRightChild();
                
                //as long as the child nodes exist, add them to the queue
                if (leftChild != null)
                    nodeQueue.enqueue(leftChild);
                if (rightChild != null)
                    nodeQueue.enqueue(rightChild);
            } else
                throw new NoSuchElementException();
                
            return currentNode.getData();
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
