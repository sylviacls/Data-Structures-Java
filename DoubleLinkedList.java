public class DoubleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Insert at the front of the list
     * 
     * @param node
     */
    public void addToFront(Node<T> node) {

        node.setNext(this.head);
        node.setPrevious(null);

        if (this.head != null) {
            this.head.setPrevious(node);
        }
        if (this.tail == null) {
            this.tail = node;
        }
        this.head = node;
        this.size++;
    }

    /**
     * Insert a node at the end of the list
     * 
     * @param node
     */
    public void addToEnd(Node<T> node) {

        node.setPrevious(tail);
        node.setNext(null);

        if (this.tail != null) {
            this.tail.setNext(node);
        }

        if (this.head == null) {
            this.head = node;
        }

        this.tail = node;
        this.size++;
    }

    /**
     * Insert the new node before the baseNode
     * 
     * @param newNode
     * @param baseNode
     */
    public boolean addBefore(Node<T> newNode, Node<T> baseNode) {
        if (this.head == null)
            return false;

        boolean result = false;

        Node<T> current = this.head;
        // searching the baseNode
        while (current != null && !current.equals(baseNode)) {
            current = current.getNext();
        }
        // found the baseNode
        if (current != null) {

            newNode.setPrevious(current.getPrevious());
            newNode.setNext(current);

            current.setPrevious(newNode);

            // handle the special case
            if (head == current) {
                this.head = newNode;
            } else {
                newNode.getPrevious().setNext(newNode);
            }

            this.size++;
            result = true;
        }

        return result;

    }

    /**
     * Remove the first node from the front of the list
     * 
     * @return the removed node
     */
    public Node<T> removeFromFront() {
        if (isEmpty())
            return null;

        Node<T> removedNode = this.head;

        // check if there's just one node in the list
        if (head.getNext() == null) {
            this.tail = null;
        }
        this.head = head.getNext();
        this.head.setPrevious(null);
        this.size--;

        return removedNode;
    }

    /**
     * Remove the last node from the end of the list
     * 
     * @return the removed node
     */
    public Node<T> removeFromEnd() {
        if (isEmpty())
            return null;

        Node<T> removedNode = this.tail;

        // check if there's just one node in the list
        if (this.tail.getPrevious() == null) {
            this.head = null;
        }
        this.tail = tail.getPrevious();
        this.tail.setNext(null);
        this.size--;

        return removedNode;
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
     * Return the Head of the list
     * 
     * @return head
     */

    public Node<T> getHead() {
        return this.head;
    }

    /**
     * Returns the tail of the list
     * 
     * @return The Tail Node
     */
    public Node<T> getTail() {
        return this.tail;
    }

    /**
     * Traverse this list and print out the node value
     *
     */
    public void print() {
        Node<T> current = this.head;
        System.out.print("Head = ");
        while (current != null) {
            System.out.print(current.getData());
            System.out.print(" <==> ");
            current = current.getNext();
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

    public static void main(String[] args) {
        DoubleLinkedList<String> linkedStrings = new DoubleLinkedList<String>();
        
        linkedStrings.addToEnd(new Node<String>("Sylvia"));
        System.out.println(linkedStrings.addBefore(new Node<String>("Danilo"),new Node<String>("Sylvia")));
        linkedStrings.addToFront(new Node<String>("Gabriella"));
        linkedStrings.addToFront(new Node<String>("Felipe"));
        linkedStrings.addToEnd(new Node<String>("Carol"));
        linkedStrings.print();
        System.out.println(linkedStrings.getSize());
        linkedStrings.addBefore(new Node<String>("Pedro"),new Node<String>("Carol") );
        linkedStrings.print();
        System.out.println(linkedStrings.getSize());
        linkedStrings.addToFront(new Node<String>("Maria"));
        linkedStrings.print();
        System.out.println(linkedStrings.getSize());

       System.out.println("Node removed = " + linkedStrings.removeFromFront().getData());
       linkedStrings.print();
       System.out.println(linkedStrings.getSize());
       System.out.println("Node removed = " + linkedStrings.removeFromEnd().getData());
       linkedStrings.print();
       System.out.println(linkedStrings.getSize());

       linkedStrings.addToFront(new Node<String>("Danilo")); 
       linkedStrings.print();
       System.out.println(linkedStrings.getSize()); 
    }

}

class Node<T> {

    private T data;
    private Node<T> nextNode;
    private Node<T> previousNode;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return nextNode;
    }

    public Node<T> getPrevious() {
        return previousNode;
    }

    public void setNext(Node<T> node) {
        this.nextNode = node;
    }

    public void setPrevious(Node<T> node) {
        this.previousNode = node;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((nextNode == null) ? 0 : nextNode.hashCode());
        result = prime * result + ((previousNode == null) ? 0 : previousNode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        @SuppressWarnings("unchecked")
        Node<T> other = (Node<T>) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        // supressed in order of testing
        /*
         * if (nextNode == null) { if (other.nextNode != null) return false; } else if
         * (!nextNode.equals(other.nextNode)) return false; if (previousNode == null) {
         * if (other.previousNode != null) return false; } else if
         * (!previousNode.equals(other.previousNode)) return false;
         */
        return true;
    }

}