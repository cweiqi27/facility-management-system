
package facilitymanagementsystem.adt;

//import ADT.ArrayStackInterface;

public class ArrayStack<T extends Comparable<T>> implements StackInterface<T> {
  private T[] array;
  private int topIndex; // index of top entry
  private static final int DEFAULT_CAPACITY = 50;

  public ArrayStack() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayStack(int initialCapacity) {
    array = (T[]) new Object[initialCapacity];
    topIndex = -1;
  }

  @Override
  public void push(T newEntry) {
    topIndex++;

    if (topIndex < array.length) {
      array[topIndex] = newEntry;
    }
  }

  @Override
  public T peek() {
    T top = null;

    if (!isEmpty()) {
      top = array[topIndex];
    }

    return top;
  } 

  @Override
  public T pop() {
    T top = null;
      System.out.println(top);
    if (!isEmpty()) {
      top = array[topIndex];
      array[topIndex] = null;
      topIndex--;
      

    } // end if
    
    return top;
  } 
  
  @Override
  public int size() {
    return this.array.length;
  }

  @Override
  public boolean isEmpty() {
    return topIndex < 0;
  } 

  public void clear() {
    topIndex = -1;
  }
    
}
