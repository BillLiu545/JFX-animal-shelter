package StackQueueTree;

/**
 * BinarySearchTree.java - Implementation of a binary search tree
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BinarySearchTree<T extends Comparable<? super T>>
extends BinaryTree<T> implements SearchTreeInterface<T>
{
    //inherit the root property from parent

    //constructors -- use the parent class's constructors
    public BinarySearchTree()
    {
        super();
    }

    public BinarySearchTree(T rootEntry)
    {
        super(rootEntry);
    }

    /** Disable the setTree method **/
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, 
    BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException();                                    
    }

    /** SearchTreeInterface Methods **/

    /** Searches for a specific entry in this tree
     *  @param anEntry - an object to be found
     *  @return - TRUE if the entry was found in the tree; FALSE otherwise
     */
    public boolean contains(T anEntry)
    {
        return getEntry(anEntry) != null;
    }

    /** Retrieves a specific entry in this tree
     *  @param anEntry - an object to be found
     *  @return either the object that was found in the tree or NULL if no such object
     *    exists
     */
    public T getEntry(T anEntry)
    {
        return findEntry(getRootNode(), anEntry);
    }

    //helper method to recursively find anEntry starting at the given node
    //  following the binary search tree algorithm
    private T findEntry(BinaryNode<T> rootNode, T anEntry)
    {
        T result = null;

        if (rootNode != null)
        {
            //grab the current entry
            T rootEntry = rootNode.getData();

            //did we find it?
            if (anEntry.equals(rootEntry))
                result = rootEntry;
            //is anEntry before current root's entry? if so, go left
            else if (anEntry.compareTo(rootEntry) < 0)
                result = findEntry(rootNode.getLeftChild(), anEntry);
            //otherwise, anEntry is bigger, so go right
            else
                result = findEntry(rootNode.getRightChild(), anEntry);
        }
        return result;
    }

    /** Adds a new entry to this tree if it does not match an existing object in the tree
     *  Otherwise replaces the existing object with the new entry
     *  @param anEntry - an object to be added to the tree
     *  @return Either null if anEntry was not in the tree but has been added, or
     *     the existing entry that matched the parameter and has been replaced
     */
    public T add(T anEntry)
    {
        T result = null;

        //Are we adding to an empty tree?
        if (isEmpty())
            setRootNode(new BinaryNode<>(anEntry));
        //otherwise, call the recursive method starting at the root
        else
            result = addEntry(getRootNode(), anEntry);

        return result;
    }

    //helper method to recursively add to nonempty subtree rooted at the given node
    private T addEntry(BinaryNode<T> rootNode, T anEntry)
    {
        T result = null;
        //save the result of the comparison
        int comparison = anEntry.compareTo(rootNode.getData());

        //if it is a match, replace the entry
        if (comparison == 0)
        {
            result = rootNode.getData();
            rootNode.setData(anEntry);
        }
        //otherwise, anEntry is smaller, so look left
        else if (comparison < 0)
        {
            //if there is a left subtree, recurse left
            if (rootNode.hasLeftChild())
                result = addEntry(rootNode.getLeftChild(), anEntry);
            //otherwise, we found where it should go
            else
                rootNode.setLeftChild(new BinaryNode<>(anEntry));
        }
        //otherwise, anEntry is bigger, so look right
        else
        {
            //if there is a right subtree, recurse right
            if (rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), anEntry);
            //otherwise, we found where it should go
            else
                rootNode.setRightChild(new BinaryNode<>(anEntry));
        }

        return result;
    }

    /** Removes a specific entry from this tree
     *  @param anEntry - an object to be removed from the tree
     *  @return Either the object that was removed from the tree, or NULL if no
     *    such object exists
     */
    public T remove(T anEntry)
    {
        T result = null;

        if (!isEmpty())
        {
            BinaryNode<T> currentNode = getRootNode();
            BinaryNode<T> parentNode = null;

            boolean found = false;

            //run binary search protocol to find the node to remove
            while (currentNode != null && !found)
            {
                int comparison = anEntry.compareTo(currentNode.getData());
                //did we find it?
                if (comparison == 0)
                {
                    result = currentNode.getData();
                    removeNode(currentNode, parentNode);    //helper method to do actual removal
                    found = true;
                } else {
                    parentNode = currentNode;   //catch up the trail node
                    //if smaller, continue left
                    if (comparison < 0)
                        currentNode = currentNode.getLeftChild();
                    //otherwise, it's bigger, so continue right
                    else
                        currentNode = currentNode.getRightChild();
                }
            }
        }

        return result;
    }

    //helper method to actually remove the nodeToRemove with the given parentNode
    private void removeNode(BinaryNode<T> nodeToRemove, BinaryNode<T> parentNode)
    {
        BinaryNode<T> leftChild = nodeToRemove.getLeftChild();
        BinaryNode<T> rightChild = nodeToRemove.getRightChild();

        //case 1: there are 2 children
        if (leftChild != null && rightChild != null)
        {
            //find the node to swap -- largest value in left subtree
            BinaryNode<T> large = leftChild;
            BinaryNode<T> trail = nodeToRemove;

            //keep going right until we can't
            while(large.getRightChild() != null)
            {
                trail = large;
                large = large.getRightChild();
            }

            //swap the data
            T data = nodeToRemove.getData();
            nodeToRemove.setData(large.getData());
            large.setData(data);

            //remove the swapped node
            removeNode(large, trail);
        } 
        //Case 2:  1 child (it's the right child)
        else if (rightChild != null)
        {
            //are we trying to remove the root node?
            if (nodeToRemove == getRootNode())
                setRootNode(rightChild);

            //otherwise, is the node to remove the parent's left child?
            else if (parentNode.getLeftChild() == nodeToRemove)
                parentNode.setLeftChild(rightChild);    //add the child as the parent's left

            //otherwise, the node to remove is the parent's right child
            else
                parentNode.setRightChild(rightChild);
        }
        //Case 3:  1 left child OR a leaf
        else {
            //are we trying to remove the root node?
            if (nodeToRemove == getRootNode())
                setRootNode(leftChild);

            //otherwise, is the node to remove the parent's left child?
            else if (parentNode.getLeftChild() == nodeToRemove)
                parentNode.setLeftChild(leftChild);

            //otherwise, the node to remove is the parent's right child
            else
                parentNode.setRightChild(leftChild);
        }
    }

    //inherit InorderIterator from BinaryTree class
}