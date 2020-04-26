import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private int size;

    /**
     * Insert at the front of the list
     * 
     * @param node
     */
    public void add(E item) {
        Node<E> node = new Node<E>(item);
        node.setNext(this.head);
        this.head = node;
        this.size++;
    }

    /**
     * Remove the first node
     * 
     * @return the removed node
     */
    public E remove() {
        if (isEmpty())
            return null;

        Node<E> removedNode = this.head;
        this.head = head.getNextNode();
        this.size--;

        return removedNode.getData();

    }

    /**
     * Return the size of the list
     * 
     * @return size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Return the Head's data of the list
     * 
     * @return head's data
     */

    public E getHeadData() {
        if (this.head != null) {
            return this.head.getData();
        }
        return null;
    }

    public Node<E> getHead() {
        return this.head;
    }
    /**
     * Traverse this list and print out the node value
     *
     */
    public void print() {
        Node<E> current = this.head;
        System.out.print("Head = ");
        while (current != null) {
            System.out.print(current.getData());
            System.out.print(" --> ");
            current = current.getNextNode();
        }
        System.out.println("null");
    }

    /**
     * Check if the list is empty
     * 
     * @return boolean that indicates if the list is empty
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>(this); 
    }

    
    public static void main(String[] args) {
        LinkedList<String> linkedStrings = new LinkedList<String>();
        linkedStrings.add("Sylvia");
        linkedStrings.add("Gabriella");
        linkedStrings.add("Felipe");

        linkedStrings.print();
        System.out.println(linkedStrings.getSize());
        System.out.println("Node removed = " + linkedStrings.remove());
        linkedStrings.print();
        System.out.println(linkedStrings.getSize());
    }

}

class ListIterator<T> implements Iterator<T> { 
    Node<T> current; 
      
    // initialize pointer to head of the list for iteration 
    public ListIterator(LinkedList<T> list) 
    { 
        current = list.getHead(); 
    } 
      
    // returns false if next element does not exist 
    public boolean hasNext() 
    { 
        return current != null; 
    } 
      
    // return current data and update pointer 
    public T next() 
    { 
        T data = current.getData(); 
        current = current.getNextNode(); 
        return data; 
    } 
      
    // implement if needed 
    public void remove() 
    { 
        throw new UnsupportedOperationException(); 
    } 
} 

class Node<E> {

    private E data;
    private Node<E> nextNode;

    public Node(E data) {
        this.data = data;
    }

    public Node<E> getNextNode() {
        return nextNode;
    }

    public void setNext(Node<E> node) {
        this.nextNode = node;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }

}