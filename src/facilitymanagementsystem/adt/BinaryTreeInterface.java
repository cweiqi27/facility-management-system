package facilitymanagementsystem.adt;

import java.util.Iterator;

/**
 *
 * @author Chan Wei Qi
 * @param <T>
 */
public interface BinaryTreeInterface<T> {
  public Iterator<T> getIterator();
  
  public boolean insert(T value);
  /**
   * Description: Insert a value into the binary tree
   * Precondition:  The value must not be null
   * Post-condition:  The value has been added into the binary tree
   * Return:  True if successful, else false
   */
  
  public boolean remove(T value);
  /**
   * Description: Remove a value from the binary tree
   * Precondition: The binary tree must exist and must contain the desired value
   * Post-condition: The desired value is removed from the tree
   * Return: True if successful, else false
   */
  
  public boolean contains(T value);
  /**
   * Description: Check if the binary tree has the value
   * Precondition:  The binary tree must exist
   * Return: True if the value exists in the binary tree, else false
   */
  
  public boolean isEmpty();
  /**
   * Description: Check if the binary tree is empty
   * Return: True if binary tree is empty, else false
   */
  
  public int getSize();
  /**
   * Description: Get the number of nodes in the binary tree
   * Return:  The number of nodes
   */
  
  public int getFrequency(T value);
  /**
   * Description: Get the number of frequency that the node has been appeared in the binary tree
   * Return:  The node frequency of T value
   */
  
  public void clear();
  /**
   * Description: Erase all the values in the binary tree
   * Post-condition:  The values in the binary tree are erased
   */
  
}
