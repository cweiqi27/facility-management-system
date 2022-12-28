package facilitymanagementsystem.adt;

/**
 *
 * @author Chan Wei Qi
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> implements BinaryTreeInterface<T> {
  private Node root;
  private int nodeCount;
  
  public AVLTree() {}
   
  public AVLTree(Node root) {
    this.root = null;
    this.nodeCount = 0;
  }
  
  @Override
  public boolean insert(T value) {
    if(value == null) return false;
    
    // check if the tree contains the value, if not then proceed
    if(!contains(root, value)) {
      root = insert(root, value);
      nodeCount++;
      return true;
    }
    
    // tree already contain value
    return false;
  }
  
  // method overloading insert as recursion method
  private Node insert(Node node, T value) {
    // insert into the current node if empty
    if(node == null)
      return new Node(value);
    
    // if current node is smaller, go left, else go right
    if(value.compareTo(node.data) < 0) {
      node.left = insert(node.left, value);
    } else if(value.compareTo(node.data) > 0) {
      node.right = insert(node.right, value);
    } else {
      node.frequency++;
    }
    
    updateHeight(node);
    return balance(node);
  }
  
  private int getHeight(Node node) {
    return node == null ? 0 : node.height;
  }
  
  // helper methods for balancing the tree
  private void updateHeight(Node node) {
    node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
  }
  
  private int getBalanceFactor(Node node) {
    return node == null ? 0 : getHeight(node.right) - getHeight(node.left);
  }
  
  private Node balance(Node node) {
    // right subtree is taller
    if(getBalanceFactor(node) < -1) {
      if(getBalanceFactor(node.left) <= 0) {
        return leftLeftCase(node);
      }
      else {
        return leftRightCase(node);
      }
    }
    
    // left subtree is taller
    if(getBalanceFactor(node) > 1) {
      if(getBalanceFactor(node.left) >= 0) {
        return rightRightCase(node);
      } else {
        return rightLeftCase(node);
      }
    }
    
    // if node's balance factor == -1, 0, or 1 then the tree is balanced
    return node;
  }
  
  private Node leftLeftCase(Node node) {
    return rotateRight(node);
  }
  
  private Node leftRightCase(Node node) {
    node.left = rotateLeft(node.left);
    return leftLeftCase(node);
  }
  
  private Node rightRightCase(Node node) {
    return rotateLeft(node);
  }
  
  private Node rightLeftCase(Node node) {
    node.right = rotateRight(node.right);
    return rightRightCase(node);
  }
  
  private Node rotateRight(Node y) {
    Node x = y.left;
    y.left = x.right;
    x.right = y;
    updateHeight(y);
    updateHeight(x);
    return x;
  }
  
  private Node rotateLeft(Node y) {
    Node x = y.right;
    y.right = x.left;
    x.left = y;
    updateHeight(y);
    updateHeight(x);
    return x;
  }
  
  @Override
  public boolean contains(T value) {
    return contains(root, value);
  }
  
  private boolean contains(Node node, T value) {
    if(node == null) return false;
    
    if(value.compareTo(node.data) < 0) 
      return contains(node.left, value);
    
    if(value.compareTo(node.data) > 0) 
      return contains(node.right, value);
    
    node.frequency++;
    return true;
  }
  
  @Override
  public boolean remove(T value) {
    if(value == null) return false;
    
    if(contains(root, value) && !isEmpty()) {
      root = remove(root, value);
      nodeCount--;
      return true;
    }
    
    return false;
  }
  
  private Node remove(Node node, T value) {
    if(value.compareTo(node.data) < 0) {
      node.left = remove(node.left, value);
    } else if(value.compareTo(node.data) > 0) {
      node.right = remove(node.right, value);
    } else {
      if(node.left == null) {
        return node.right;
      } else if(node.right == null) {
        return node.left;
      } else {
        if(node.left.height > node.right.height) {
          T succesorValue = findMax(node.left);
          node.data = succesorValue;
        } else {
          T succesorValue = findMin(node.right);
          node.data = succesorValue;
          node.right = remove(node.right, succesorValue);
        }
      }
    }
    
    updateHeight(node);
    return balance(node);
  }
  
  // Find the largest value on the left subtree
  private T findMax(Node node) {
    while(node.left != null)
      node = node.left;
    
    return node.data;
  }
  
  // Find the smallest value on the right subtree
  private T findMin(Node node) {
    while(node.right != null)
      node = node.right;
    
    return node.data;
  }
  
  @Override
  public int getSize() {
    return this.nodeCount;
  }
  
  @Override
  public void clear() {
    this.root = null;
  }
  
  @Override
  public boolean isEmpty() {
    return root == null;
  }

  private class Node {
    T data;
    int height;
    int frequency;
    Node left;
    Node right;
    
    public Node(T data) {
      this.data = data;
    }
  }
  
   // returns as iterator to traverse the tree in order
  @Override
  public java.util.Iterator<T> iterator() {
    final int expectedNodeCount = nodeCount;
    final java.util.Stack<Node> stack = new java.util.Stack<>();
    stack.push(root);

    return new java.util.Iterator<T>() {
      Node trav = root;

      @Override
      public boolean hasNext() {
        if (expectedNodeCount != nodeCount) 
          throw new java.util.ConcurrentModificationException();
        
        return !isEmpty() && !stack.isEmpty();
      }

      @Override      
      public T next() {

        if (expectedNodeCount != nodeCount) 
          throw new java.util.ConcurrentModificationException();

        while (trav != null && trav.left != null) {
          stack.push(trav.left);
          trav = trav.left;
        }

        Node node = stack.pop();

        if (node.right != null) {
          stack.push(node.right);
          trav = node.right;
        }

        return node.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}
 