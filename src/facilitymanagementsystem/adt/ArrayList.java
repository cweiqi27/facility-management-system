/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facilitymanagementsystem.adt;

/**
 *
 * @author Thomas
 * @param <T>
 */
public class ArrayList<T> implements ListInterface<T>{
  private T[] array;
  private int numberOfEntries;
  private static final int DEFAULT_CAPACITY = 5;
  
  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }
  
    public ArrayList(int initialCapacity) {
    numberOfEntries = 0;
    array = (T[]) new Object[initialCapacity]; // initialize purpose
    }
  
    @Override
    public boolean add(T newEntry){
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }
    
    @Override
    public boolean add(int newPosition, T newEntry){
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
          isSuccessful = false;
        }

        return isSuccessful;
    }
    
    @Override
    public T remove(int givenPosition){
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
          result = array[givenPosition - 1];

          if (givenPosition < numberOfEntries) {
            removeGap(givenPosition);
          }

          numberOfEntries--;
        }

        return result;
    }
    
    @Override
    public void clear() {
        numberOfEntries = 0;
    }
    
    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
          array[givenPosition - 1] = newEntry;
        } else {
          isSuccessful = false;
        }

        return isSuccessful;
    }
    
    @Override
    public boolean contains(T anEntry) {
      boolean found = false;
      for (int index = 0; !found && (index < numberOfEntries); index++) {
        if (anEntry.equals(array[index])) {
          found = true;
        }
      }
      return found;
    }
    
    @Override
    public T get(int givenPosition) {
      return !isEmpty() ? array[givenPosition] : null;
    }
    
    @Override
    public boolean isEmpty() {
      return numberOfEntries == 0;
    }
    
    @Override
    public int size() {
        return numberOfEntries;
    }
    
    @Override
    public int indexOf(T anEntry) {
      boolean found = false;
      int i = 0;
      for (int index = 0; !found && (index < numberOfEntries); index++) {
        if (anEntry.equals(array[index])) {
          i = index;
          found = true;
        }
      }
      
      return i;
    }
    
    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
          outputStr += array[index] + "\n";
        }

        return outputStr;
      }
    
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
          array[index + 1] = array[index];
        }
    }
    
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
          array[index] = array[index + 1];
        }
    }

  @Override
  public boolean replace(T oldElement, T newElement) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }
    
}
