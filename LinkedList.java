public class LinkedList<T> {

    private Node<T> head;
    private int size;
    
    /**
	 * Insert at the front of the list
	 * @param node
	 */
    public void add(Node<T> node) {
        node.setNext(this.head);
        this.head = node;
        this.size++;
    }

   /**
    * Remove the first node 
    * @return the removed node
    */
    public Node<T> remove() {
        if(isEmpty()) return null;

        Node<T> removedNode = this.head;
        this.head = head.getNextNode();
        this.size--;

        return removedNode;

    }
    /**
     * Return the size of the list
     * @return size
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Return the Head of the list
     * @return head
     */

    public Node<T> getHead() {
        return head;
    }

    /**
	 * Traverse this list and print out the node value
	 *
     * */
    public void print() {
        Node<T> current = this.head; 
        System.out.print("Head = ");
        while(current != null) {
            System.out.print(current.getData());
            System.out.print(" --> ");
            current = current.getNextNode();
        }
        System.out.println("null");
    }
    /**
     * Check if the list is empty
     * @return boolean that indicates if the list is empty
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    public static void main(String[] args) {
        LinkedList<String> linkedStrings = new LinkedList<String>();
        linkedStrings.add(new Node<String>("Sylvia"));
        linkedStrings.add(new Node<String>("Gabriella"));
        linkedStrings.add(new Node<String>("Felipe"));

        linkedStrings.print();
        System.out.println(linkedStrings.getSize());
        System.out.println("Node removed = " + linkedStrings.remove().getData());
        linkedStrings.print();
        System.out.println(linkedStrings.getSize());
    }


}

class Node<T> {

    private T data;
    private Node<T> nextNode;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNext(Node<T> node) {
        this.nextNode = node;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}