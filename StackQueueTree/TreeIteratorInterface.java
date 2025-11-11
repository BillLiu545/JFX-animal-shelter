package StackQueueTree;


/**
 * TreeIteratorInterface.java - generalized interface for traversal methods of a tree
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Iterator;

public interface TreeIteratorInterface<T>
{
     public Iterator<T> getPreorderIterator();
     public Iterator<T> getInorderIterator();
     public Iterator<T> getPostorderIterator();
     public Iterator<T> getLevelOrderIterator();
}
