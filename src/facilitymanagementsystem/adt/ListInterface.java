/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facilitymanagementsystem.adt;

/**
 *
 * @author Thomas, Wen Jie
 */
public interface ListInterface<T> {
    public boolean add(T newElement);
    
    public boolean add(int newPosition, T newElement);
    
    public T remove(int givenPosition);
    
    public boolean contains(T anElement);
    
    public boolean replace(int givenPosition, T anElement);
    
    public boolean replace(T oldElement, T newElement);
    
    public T get(int givenPosition);
    
    public int indexOf(T anEntry);
    
    public boolean isEmpty();
    
    public int size();
    
    public void clear();
}
