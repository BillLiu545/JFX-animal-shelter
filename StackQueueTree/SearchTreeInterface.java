package StackQueueTree;
import java.util.Iterator;


/**
 * SearchTreeInterface.java - interface for a search tree
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface SearchTreeInterface<T extends Comparable<? super T>>
            extends TreeInterface<T>
{
    /** Searches for a specific entry in this tree
     *  @param anEntry - an object to be found
     *  @return - TRUE if the entry was found in the tree; FALSE otherwise
     */
    public boolean contains(T anEntry);
    
    /** Retrieves a specific entry in this tree
     *  @param anEntry - an object to be found
     *  @return either the object that was found in the tree or NULL if no such object
     *    exists
     */
    public T getEntry(T anEntry);
    
    /** Adds a new entry to this tree if it does not match an existing object in the tree
     *  Otherwise replaces the existing object with the new entry
     *  @param anEntry - an object to be added to the tree
     *  @return Either null if anEntry was not in the tree but has been added, or
     *     the existing entry that matched the parameter and has been replaced
     */
    public T add(T anEntry);
    
    /** Removes a specific entry from this tree
     *  @param anEntry - an object to be removed from the tree
     *  @return Either the object that was removed from the tree, or NULL if no
     *    such object exists
     */
    public T remove(T anEntry);
    
    /** Creates an iterator that traverses all entries in this tree
     *  @return AN iterator that provides sequential and ordered access to the entries
     *     in the tree
     */
    public Iterator<T> getInorderIterator();
    
}
