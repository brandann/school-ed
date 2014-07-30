/*
 * CircularDoublyLinkedList.java
 */
package CircularDoublyLinkedList;
import java.io.Serializable;
import java.util.*;

/**
 * A Circular Doubly-Linked List class
 * @author pbladek, Brandan Haertel
 * @version 1.1 05/29/2013 <br>
 *          1.0 05/06/2013 <br>
 * Test Environment: JDK 1.7.0_15 on Windows 8, AMD A8 CPU<br />
 * History:
 *          1.0 - original release
 *          1.1 - Implemented all SimpleLinkedList Options
 *                allowed negative index
 *                Fixed so getNodeAt is effetintly searching based on 
 *                  optomised index locations.
 *                Various Misc fixes.
 * @param <E> the data type
 */
public class CircularDoublyLinkedList<E>  extends AbstractList<E>
    implements List<E>, Cloneable, Serializable
{
    public static final int ITEM_NOT_FOUND = -1;
    /**
     * Inner class Node
     * @param <E> the data type
     */
    protected class Node<E>                                                     
    {
        protected E element;
        protected Node<E> next;
        protected Node<E> previous;

        /**
         * constructor
         * @param element the data element
         */
        protected Node(E element)                                               
        {
            this.element = element;
            next = null;
            previous = null;
        }

        /**
         * accessor for element
         * @return the element
         */
        protected E getElement()                                                
        {
            return element;
        }
        /**
         * constructor
         * @param element the data element
         * @param next reference to the next Node
         * @param previous reference to the previous Node
         */        
        protected Node(E element, Node<E> next, Node<E> previous)               
        {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
  
        /**
         * Return the data as a string
         * @return the data as a string
         */
        @Override
        public String toString()                                                
        {
            return "->" + element.toString();
        }
        
    }
   
    /**
     * Inner class SimpleLinkedListIterator
     */
    protected class SimpleLinkedListIterator implements ListIterator<E>          
    {
        protected Node<E> next;
        protected Node<E> lastItemReturned;
        protected int index = 0;
        protected boolean removable = false;
        
        /**
         * Constructs a SimpleLinkedListIterator.
         * 
         * @param i int of Iterator starting position.
         */
        protected SimpleLinkedListIterator(int i)                               
        {
            if(index < (length * -1) || index > length){
                throw new IndexOutOfBoundsException();
            }
            if (i == 0){
                next = head;
            }
            else if (i == length) {
                next = getTail();
            }
            else {
                next = getNodeAt(i);
            }
            lastItemReturned = null; // No item returned yet.
        }
        
        /** 
        *  Returns the element this Iterator object was (before this call) 
        *  positioned at, and advances this Iterator object.
        *                    
        *  @return - the element this Iterator object was positioned at.
        */                            
        @Override
        public E next() throws NoSuchElementException
        {
            E theElement = next.element;
            lastItemReturned = next;
            next = next.next;
            index++;
            if(next.equals(head))
                index = 0;
            removable = true;
            return theElement;
        }
        
       /**
        *  Determines if this Iterator object is positioned at an element
        *   in this Collection.
        *  @return true - if this Iterator object is positioned at an element; 
        *    otherwise, false.                        
        */                   
        @Override
        public boolean hasNext()
        {
            if(length == 0)
                return false;
            return true;
        }
        
        /**
        * Removes from the underlying collection the last element returned by
        *   this iterator (optional operation). This method can be called only
        *   once per call to next(). The behavior of an iterator is
        *   unspecified if the underlying collection is modified while the
        *   iteration is in progress in any way other than by calling this
        *   method.
        * @throws IllegalStateException - if next() had not been called before
        *   this call to remove(), or if there had been an intervening call 
        *   to remove() between the most recent call to next() and this call.                
        */
        @Override
        public void remove()
        {
            if(index < 0 || index >= length)
            throw new IndexOutOfBoundsException();
            if(!removable)
                throw new IllegalStateException();
            removable = false;
            if(index == 0)
            {
                Node<E> temp = head;
                head = head.next;
                length--;
                return;
            }
            Node<E> nodeBefore = getNodeAt(index - 1);
            if(nodeBefore == null)
                throw new IndexOutOfBoundsException();
            Node<E> oldNode = getNodeAt(index);
            Node<E> previousNode = oldNode.previous;
            Node<E> nextNode = oldNode.next;
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
            length--;
        }
        
        /**
         * Adds object to the tail of the linkedlist.
         * 
         * @param obj E to be added to the linkedlist.
         */
        @Override
        public void add(E obj) 
        {           //
            if(head == null)
            {
                head = new Node<>(obj);
                head.next = head;
                head.previous = head;
                length++;
                return;
            }
            Node<E> newNode = new Node(obj);
            Node<E> tail = getTail();
            newNode.previous = tail;
            newNode.next = head;
            tail.next = newNode;
            head.previous = newNode;
            length++;
        }
        
        /**
         * Set the current index nodes element to the passed in object.
         * 
         * @param obj E to be set to the current position.
         */
        @Override
        public void set(E obj) 
        {   
            Node<E> node = getNodeAt(index);
            node.element = obj;
        }

       /**
        *  Determines if this Iterator has a previous node.
        * 
        *  @return true - if this Iterator has a previous Node                        
        */     
        @Override
        public boolean hasPrevious() 
        {    //
            if(length == 0)
                return false;
            return true;
        }

        /** 
        *  Returns the element this Iterator object was (before this call) 
        *  positioned at, and retreats this Iterator object.
        *                    
        *  @return - the element this Iterator object was positioned at.
        */        
        @Override
        public E previous()
        {
            removable = true;
            E theElement = next.element;
            lastItemReturned = next;
            next = next.previous;
            index--;
            if(next.equals(head))
                index = 0;
            return theElement;
        }

        /**
         * Returns the index of the next node.
         * 
         * @return int - index of the next node.
         */
        @Override
        public int nextIndex()
        {
            if(next.next.equals(head))
                return 0;
            return index + 1;
        }

        /**
         * Returns the index of the previous node.
         * 
         * @return int - index of the previous node.
         */
        @Override
        public int previousIndex()
        {
            if(next.previous.equals(head))
                return 0;
            return index - 1;
        }
    }
    
    /**
     * default constructor -- creates an empty list
     */
    public CircularDoublyLinkedList()
    {
        head = null;
    }
    
    /**
     * Appends the specified element to the end of this list.
     * @param e element to be appended to this list
     * @return true (as specified by Collection.add(E))
     * @throws UnsupportedOperationException  if the add operation is not
     *   supported by this list
     * @throws ClassCastException if the class of the specified element
     *   prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *   list does not permit null element
     * @throws IllegalArgumentException if some property of this element
     *   prevents it from being added to this list   
     */
    @Override
    public boolean add(E e) throws UnsupportedOperationException,               
        ClassCastException, NullPointerException, IllegalArgumentException
    {
        if(head == null)
        {
            head = new Node<>(e);
            head.next = head;
            head.previous = head;
            length++;
            return true;
        }
        
        //ORIGINAL CODE
        /*
        Node<E> tail = getTail();
        tail.next = new Node(e);
        length++;
        */
        
        //Brandans revised code
        Node<E> newNode = new Node(e);
        Node<E> tail = getTail();
        newNode.previous = tail;
        newNode.next = head;
        tail.next = newNode;
        head.previous = newNode;
        length++;
        return true;
    }
    
    /**
     * Inserts the specified element at the specified position in this list.
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException  if the add operation is not
     *   supported by this list
     * @throws ClassCastException if the class of the specified element
     *   prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *   list does not permit null element
     * @throws IllegalArgumentException if some property of this element
     *   prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *   (index < 0 || index > size())
     */
    @Override
    public void add(int index, E element) throws                                
            UnsupportedOperationException,
            ClassCastException,
            NullPointerException,
            IllegalArgumentException,
            IndexOutOfBoundsException
    { 
        if(index < (length * -1) || index > length){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0)
        {
            Node<E> tempHead = head;
            head = new Node<>(element);
            head.previous = tempHead.previous;
            head.next = tempHead;
            tempHead.previous = head;
            length++;
            return;
        }
        if(index == length)
        {
            add(element);
            return;
        }

        //Brandans idea of how this code should go...
        Node<E> newNode = new Node(element);
        Node<E> previousOfNew = this.getNodeAt(index - 1);
        Node<E> nextOfNew = previousOfNew.next;
        newNode.previous = previousOfNew;
        newNode.next = nextOfNew;
        previousOfNew.next = newNode;
        nextOfNew.previous = newNode;
        length++;
        // ORIGINAL CODE
        /*
        Node<E> nodeAtPrevious = getNodeAt(index - 1);
        if(nodeAtPrevious == null)
            throw new IndexOutOfBoundsException();
        nodeAtPrevious.next = new Node<>(element, nodeAtPrevious.next);
        */
    }

    /**
     * Appends all of the elements in the specified collection to the end
     * of this list, in the order they are returned by the specified
     * collection's iterator (optional operation)
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not
     *   supported by this list
     * @throws ClassCastException if the class of an element of the specified
     *   collection prevents it from being added to this list
     * @throws NullPointerException if the specified collection contains one
     *   or more null elements and this list does not permit null elements,
     *   or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     *   specified collection prevents it from being added to this list
     */
    @Override
    public boolean addAll(Collection<? extends E> c)                            
            throws UnsupportedOperationException,
            ClassCastException, NullPointerException,
            IllegalArgumentException
    {
        Iterator<? extends E> itr = c.iterator();
        while(itr.hasNext())
        {
            add(itr.next());
        }
        return true;
         
    }
    
     /**
     * Inserts all of the elements in the specified collection into this list
     * at the specified position (optional operation).
     * @param c collection containing elements to be added to this list
     * @param index index at which to insert the first element from the
     *   specified collection
     * @return true if this list changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not
     *   supported by this list
     * @throws ClassCastException if the class of an element of the specified
     *   collection prevents it from being added to this list
     * @throws NullPointerException if the specified collection contains one
     *   or more null elements and this list does not permit null elements,
     *   or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     *   specified collection prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *   (index < 0 || index > size())
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c)                 
            throws UnsupportedOperationException,
            ClassCastException, NullPointerException,
            IllegalArgumentException,
            IndexOutOfBoundsException
    {
        Iterator<? extends E> itr = c.iterator();
        while(itr.hasNext())
        {
            add(index,itr.next());
        }
        return true;
    }

    /**
     * Removes all of the elements from this list.
     * @throws UnsupportedOperationException if the clear operation is not
     *   supported by this list
     */
    @Override
    public void clear() throws UnsupportedOperationException                   
    {
        Node<E> currentNode = head;
        for(int i = 0; i < length; i++)
        {
            currentNode.previous = null;
            currentNode = currentNode.next;
        }
        Node<E> tail = getTail();
        head.previous = null;
        head.next = null;
        head = null;
        tail.previous = null;
        tail.next = null;
        tail = null;
        
        length = 0;
    }
    /**
     * Returns a shallow copy of this LinkedList.
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    /**
     * Returns true if this list contains the specified element.
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element.
     */
    @Override
    public boolean contains(Object o)
    {
        for(Node<E> current = head; current != null; current = current.next)
            if(current.element.equals(o))
                return true;
        return false;
    }
    
    /**
     * Returns true if this list contains all of the elements
     * of the specified collection.
     * @param c collection containing elements whose presence in this list are
     *   to be tested
     * @return return true if this list contains all of the specified elements
     * @throws ClassCastException if the types of one or more elements in the
     *   specified collection are incompatible with this list (optional)
     * @throws NullPointerException if the specified collection contains one
     *   or more null elements and this list does not permit null elements
     *   (optional), or if the specified collection is null
     */
    @Override
    public boolean containsAll(Collection<?> c)
    {
        Iterator<?> itr = c.iterator();
        while(itr.hasNext())
        {
            if(!contains(itr.next()))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Compares the specified object with this list for equality.
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal to this list
     */
    @Override
    public boolean equals(Object o) 
    {
        if(o == null)
            return false;
        if(o == this)
            return true;
        return false;
    }
  
    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *   (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException  
    {
         Node<E> node = getNodeAt(index);
         return node.getElement();     
    }
 
    /**
     * Returns the hash code value for this list.
     * @return the hash code value for this list
     */
    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 41 * hash + this.length;
        return hash;  
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * n this list, or -1 if this list does not contain the element.
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *   this list, or -1 if this list does not contain the element
     * @throws ClassCastException if the type of the specified element is
     *   incompatible with this list (optional)
     * @throws NullPointerException if the specified element is null and this
     *   list does not permit null elements (optional)
     */
    @Override
    public int indexOf(Object o) throws ClassCastException,  
        NullPointerException
    {
        int count = 0;
        if(o == null)
        {
            //for(Node<E> current = head; current != null; 
                    //current = current.next)
            Node<E> current = head;
            for(int i = 0; i < length; i++)
            {
                if(current.getElement() == null)
                    return i;
                current = current.next;
            }   
        }else
        {
            E searchElement = (E)o;
            //for(Node<E> current = head; current != null; 
                    //current = current.next)
            Node<E> current = head;
            for(int i = 0; i < length; i++)
            {
                if(searchElement.equals(current.getElement()))
                    return i;
                current = current.next;
            }
        }
        return ITEM_NOT_FOUND;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty()  
    {
        if(length == 0)
            return true;
        return false;
    }
    
    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() 
    {
         return new SimpleLinkedListIterator(0);     
    }
    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     *   this list, or -1 if this list does not contain the element
     * @throws ClassCastException - if the type of the specified element is
     *   incompatible with this list (optional)
     * @throws NullPointerException - if the specified element is null and
     *   this list does not permit null elements (optional)
     */
    @Override
    public int lastIndexOf(Object o) throws ClassCastException, 
        NullPointerException
    { 
        
        int lastIndex = ITEM_NOT_FOUND;
        try
        {
             lastIndex = lastIndexOf((E)o, head, 0);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(e);
        }
        return lastIndex;
    }

    /**
     * Returns the index of the last occurrence of the specified element --
     *   recursive
     * @param searchElement element to search for
     * @param node reference to current node
     * @param index the current index
     * @return the index of the last occurrence of the specified element in
     *   this list, or -1 if this list does not contain the element
     * @throws IndexOutOfBoundsException if (index < 0 || index >= length)
     */
    protected int lastIndexOf(E searchElement, Node<E> node, int index)
            throws IndexOutOfBoundsException
    { 
        if(index < (length * -1) || index > length){
            throw new IndexOutOfBoundsException();
        }
         int FoundIndex = ITEM_NOT_FOUND;
         if(node == null)
            return FoundIndex;
         if(node.next != null)
             FoundIndex = lastIndexOf(searchElement, node.next, index + 1);
         if(searchElement == null)
         {
            if(node.getElement() == null)
                if(FoundIndex == ITEM_NOT_FOUND)
                    FoundIndex = index;    
         }
         if(searchElement.equals(node.getElement()))
             if(FoundIndex == ITEM_NOT_FOUND)
                 FoundIndex = index;
         return FoundIndex;
    }

    /**
     * Returns a list iterator over the elements in this list
     *   (in proper sequence).
     * @return a list iterator over the elements in this list
     *   (in proper sequence)
     */
    @Override
    public ListIterator<E> listIterator()  
    {
        return new SimpleLinkedListIterator(0);     
    }
    
    /**
     * Returns a list-iterator of the elements in this list
     * (in proper sequence), starting at the specified position in the list.
     * @param index index of the first element to be returned from the list
     *   iterator (by a call to next)
     * @return a list iterator over the elements in this list
     *   (in proper sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *   (index < 0 || index > size())
     */
    @Override
    public ListIterator<E> listIterator(int index) 
            throws IndexOutOfBoundsException
    {
        return new SimpleLinkedListIterator(index);       
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index index of the first element to be returned from the list
     *   iterator (by a call to next)
     * @return a list iterator over the elements in this list
     *   (in proper sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *   (index < 0 || index > size())
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException 
    {
        if(index < (length * -1) || index > length){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0)
        {
            Node<E> temp = head;
            head = head.next;
            length--;
            return temp.getElement();
        }
        Node<E> nodeBefore = getNodeAt(index - 1);
        if(nodeBefore == null)
            throw new IndexOutOfBoundsException();
        //OLD CODE TO BE REVISED
        /*
        Node<E> temp = nodeBefore.next;
        nodeBefore.next = temp.next;
        */
        
        //NEW BRANDAN CODE
        Node<E> oldNode = this.getNodeAt(index);
        Node<E> previousNode = oldNode.previous;
        Node<E> nextNode = oldNode.next;
        previousNode.next = nextNode;
        nextNode.previous = previousNode;
        oldNode.next = null;
        oldNode.previous = null;
        
        length--;
        return oldNode.getElement();      
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     * @throws ClassCastException - if the type of the specified element is
     *   incompatible with this list (optional)
     * @throws NullPointerException - if the specified element is null and
     *   this list does not permit null elements (optional)
     * @throws UnsupportedOperationException - if the remove operation is
     *   not supported by this list
     */
    @Override
    public boolean remove(Object o) throws ClassCastException, 
        NullPointerException, UnsupportedOperationException
    {
        if(this.contains(o))
        {
            this.remove(this.indexOf(o));
            return true;
        }
        return false;
    }

    /**
     * Removes from this list all of its elements that are contained
     * in the specified collection (optional operation).
     * @param c collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     * @throws UnsupportedOperationException if the removeAll operation is not
     *   supported by this list
     * @throws ClassCastException if the class of an element of this list is
     *   incompatible with the specified collection (optional) 
     * @throws NullPointerException if this list contains a null element and
     *   the specified collection does not permit null elements (optional),
     *   or if the specified collection is null
     */
    @Override
    public boolean removeAll(Collection<?> c) 
            throws UnsupportedOperationException
    {
       throw new UnsupportedOperationException();
    }

    
    /**
     * Retains only the elements in this list that are contained
     * in the specified collection (optional operation).
     * @param c collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     * @throws UnsupportedOperationException if the retainAll operation is not
     *   supported by this list
     * @throws ClassCastException if the class of an element of this list is
     *   incompatible with the specified collection (optional)
     * @throws  NullPointerException if this list contains a null element and
     *   the specified collection does not permit null elements (optional),
     *   or if the specified collection is null
     */
    @Override
    public boolean retainAll(Collection<?> c) 
            throws UnsupportedOperationException, ClassCastException,
            NullPointerException
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the set operation is not
     *   supported by this list
     * @throws ClassCastException if the class of the specified element
     *   prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and
     *   this list does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *   element prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *   (index < 0 || index >= size()) 
     */
    @Override
    public E set(int index, E element) throws UnsupportedOperationException, 
        ClassCastException, NullPointerException, IllegalArgumentException,
        IndexOutOfBoundsException
    {
        Node<E> node = this.getNodeAt(index);
        E previousElement = node.element;
        node.element = element;
        return previousElement;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size()
    {
       return length;       
    }
    
    /**
     * Returns a view of the portion of this list between the specified
     * fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *   (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) 
            throws IndexOutOfBoundsException
    {
        if(fromIndex < (length * -1) || toIndex > length)
            throw new IndexOutOfBoundsException();
        List<E> list = new LinkedList<>();
        for(int i = fromIndex; i < toIndex; i++)
        {
            list.add(this.get(i));
        }
        return list;
    }
    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     * @return an array containing all of the elements in this list
     *   in proper sequence
     */
    @Override
    public Object[] toArray() 
    {
        Object[] obj = null;
        if(this.length > 0)
        {
            Iterator itr = this.iterator();
            obj = new Object[this.length];
            for(int i = 0; i < this.length; i++)
            {
                obj[i] = itr.next();
            }
        }
        return obj;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the runtime type of the returned
     * array is that of the specified array.
     * @param a the array into which the elements of this list are to be
     *   stored, if it is big enough; otherwise, a new array of the same
     *   runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
     */
    @Override
    public <T> T[] toArray(T[] a)  
    {
        if(this.length > a.length) {
            return (T[]) this.toArray();
        }
        else{
            a = (T[]) this.toArray();
            return a;
        }
    }
    /**
    * Return the data as a string
    * @return the data as a string
    */
    @Override
    public String toString()   
    {
        String string = "";
        Node<E> current = head;
        for(int i = 0; i < length; i++)
        {
            string += current.toString();
            current = current.next;
        }
        return string;
    }
    
    /**
     * Returns a reference to the last Node
     * @return a reference to the last Node
     */
    protected Node<E> getTail()
    {
        Node<E> h = head;
        return h.previous;
           
    }
       
    /**
     * Returns a reference to the last Node
     * @return a reference to the last Node
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     */
    protected Node<E> getNodeAt(int index) throws IndexOutOfBoundsException 
    {
        if(index < (length * -1) || index > length){
            throw new IndexOutOfBoundsException();
        }
        
        if(index < (-length / 2)){
            Node<E> node = getTail();
            for(int i = 0; i < index; i++)
                node = node.next;
            return node;
        }
        if((index <= 0) && (index > (-length / 2))){
            Node<E> node = head;
            for(int i = 0; i < index; i--)
                node = node.previous;
            return node;
        }
        if((index >= 0) && (index < (length / 2))){
            Node<E> node = head;
            for(int i = 0; i < index; i++)
                node = node.next;
            return node;
        }
        else{
            Node<E> node = head;
            for(int i = length; i > index; i--)
                node = node.previous;
            return node;
        }
    }
   
    protected int length = 0;
    protected Node<E> head;
}
