/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facilitymanagementsystem.adt;

/**
 *
 * @author Chan Wei Qi
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> implements BinaryTreeInterface<T> {
  private Node root;
  
  public AVLTree() {}
  
  public AVLTree(Node root) {
    root = null;
  }
  
  @Override
  public void insert(T value) {  
    root = insertRecursive(root, value);
  }
  
  private Node insertRecursive(Node node, T value) {
    if(root == null) return new Node(value);
    
    if(root.data.compareTo(value) < 0) {
      if(node.left == null)
        node.left = new Node(value);
      else
        insertRecursive(node.left, value);
    } else if(root.data.compareTo(value) > 0) {
      if(node.right == null)
      insertRecursive(node.right, value);
    } else {
      return new Node(value);
    }
    
    return null;
  }
  
  public boolean inOrder() {
    return false;
  }
  
  @Override
  public void remove(T value) {
    root = removeRecursive(root, value);
  }
  
  private Node removeRecursive(Node node, T value) {
    if(root == null) return root;
  }
  
  public boolean isEmpty() {
    return root == null;
  }

  private class Node {
    T data;
    Node left;
    Node right;
    
    public Node(T data) {
      this.data = data;
    }
  }
}
