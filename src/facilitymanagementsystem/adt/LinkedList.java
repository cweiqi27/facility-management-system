package facilitymanagementsystem.adt;


public class LinkedList<T> implements ListInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedList() {
        clear();
    }
    
    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
  }

    
    @Override
    public boolean add(T newElement) {
      Node newNode = new Node(newElement);

      if (isEmpty()) {
        firstNode = newNode;
      } else {                    
        Node currentNode = firstNode;	
        while (currentNode.next != null) {
          currentNode = currentNode.next;
        }
        currentNode.next = newNode;
      }

      numberOfEntries++;
      return true;
    }

    @Override
    public boolean add(int newPosition, T newElement) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
        Node newNode = new Node(newElement);

            if (isEmpty() || (newPosition == 1)) {
              newNode.next = firstNode;
              firstNode = newNode;
            } else {
              Node nodeBefore = firstNode;
              for (int i = 1; i < newPosition - 1; ++i) {
                nodeBefore = nodeBefore.next;
              }

              newNode.next = nodeBefore.next;
              nodeBefore.next = newNode;
            }
            numberOfEntries++;
            
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
  }
    
    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            if (givenPosition == 1) {
              result = firstNode.data;
              firstNode = firstNode.next;
            } else {
                Node nodeBefore = firstNode;
                for (int i = 1; i < givenPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;
                }
                result = nodeBefore.next.data;
                nodeBefore.next = nodeBefore.next.next;
            }

            numberOfEntries--;
        }
        return result;
    }
    
    @Override
    public boolean replace(T oldElement, T newElement) {
        if(isEmpty()){
            return false;
        }
        else if (firstNode.data.equals(oldElement)) {
            firstNode.data=newElement;
            return true;
        } else {
            Node currentNode = firstNode;
            while (currentNode.next != null) {
                if (currentNode.next.data.equals(oldElement)) {
                    Node newNode=new Node(newElement);
                    newNode.next=currentNode.next.next;
                    currentNode.next=newNode;
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    @Override
    public T get(int givenPosition) {
        T result = null;

        if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition; ++i) {
                currentNode = currentNode.next;		// advance currentNode to next node
            }
            result = currentNode.data;	// currentNode is pointing to the node at givenPosition
        }
        return result;
    }

    @Override
    public boolean contains(T anElement){
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anElement.equals(currentNode.data)) {
            found = true;
            } else {
            currentNode = currentNode.next;
            }
        }
        return found;
    }
    
    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }
    
    @Override
    public String toString() {
      String outputStr = "";
      Node currentNode = firstNode;
      while (currentNode != null) {
        outputStr += currentNode.data + "\n";
        currentNode = currentNode.next;
      }
      return outputStr;
    }

  @Override
  public boolean replace(int givenPosition, T anElement) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public int indexOf(T anEntry) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

    private class Node {

    private T data;
    private Node next;

    private Node(T data) {
      this.data = data;
      this.next = null;
    }

    private Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }
  }
}
