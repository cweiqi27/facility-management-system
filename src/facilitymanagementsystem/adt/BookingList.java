
package facilitymanagementsystem.adt;


public class BookingList<T extends Comparable<T>> implements ListInterface<T> {
    private T[] elementData; 
    private int size;        

    public static final int DEFAULT_CAPACITY = 100;

    public BookingList() {
        this(DEFAULT_CAPACITY);
    }
    
    public BookingList(int capacity) {
        size = 0;
        elementData = (T[]) new Comparable[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        //checkIndex(index);
        return elementData[index];
    }

    @Override
    public String toString() {
        String outputStr = "";
        for(int index = 0; index < size;++index){
            outputStr += elementData[index] + "\n";
        }
        return outputStr;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        boolean found = false;
        for(int index = 0; !found && (index < size);index++){
            if(value.equals(elementData[index])){
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean add(T value) {
        int i = 0;
        while(i < size && value.compareTo(elementData[i]) > 0){
            i++;
        }
        addBooking(i + 1);
        elementData[i] = value;
        //elementData[size] = value;
        size++;
        return true;
    }

    public boolean remove(T value) {
        if(size == 0){
            return false;
        }else{
            int index = 0;
            while(index<size && elementData[index].compareTo(value)<0){
                index++;
            }
            if(elementData[index].equals(value)){
                removeGap(index+1);
                size--;
                return true;
            }
        }
        return false;
    }

    public void set(int index, T value) {
        checkIndex(index);
        elementData[index] = value;
    }
    
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: ");
        }
    }

    // post: list is empty
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    
    private boolean isArrayFull(){
        return size == elementData.length;
    }
    private void doubleArray() {
        T[] oldList = elementData;
        int oldSize = oldList.length;
        
        elementData = (T[]) new Object[2 * oldSize];
        for(int index = 0; index < oldSize;index++){
            elementData[index] = oldList[index];
        }
    }
    
    private void addBooking(int newpos){
        int newindex = newpos - 1;
        int lastindex = size - 1;
        for(int index = lastindex;index >= newindex;index--){
            elementData[index + 1] = elementData[index];
        }
    }
    
    private void removeGap(int givenpos){
        int removeindex = givenpos - 1;
        int lastindex = size - 1;
        for(int index = removeindex;index < lastindex;index++){
            elementData[index] = elementData[index + 1];
        }
    }
    
    public T remove(int h) {
        for (int i = size; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return null;
    }

  @Override
  public boolean add(int newPosition, T newElement) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public boolean replace(int givenPosition, T anElement) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean replace(T oldElement, T newElement) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public int indexOf(T anEntry) {
    throw new UnsupportedOperationException("Not supported yet."); 
  }
}

