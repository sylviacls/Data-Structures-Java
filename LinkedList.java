public class LinkedList<T> {

    private Node<T> head;
    
    /**
	 * Insert at the front of the list
	 * @param node
	 */
    public void add(Node<T> node) {
        node.setNext(this.head);
        this.head = node;
    }

    /**
	 * Remove from the front of the list
	 */
    public void remove() {
        if(head.getNextNode() != null) {
            this.head = head.getNextNode();
        } else {
            this.head = null;
        }

    }

    public Node<T> getHead() {
        return head;
    }
    /**
	 * Recursively traverse this list and print out the node value
	 * @param node
	 */
    public void print(Node<T> node) {
        System.out.println("Node is " + node.getData());
        if(node.getNextNode() != null) print(node.getNextNode());
    }


    public static void main(String[] args) {
        LinkedList<String> linkedStrings = new LinkedList<String>();
        linkedStrings.add(new Node<String>("Sylvia"));
        linkedStrings.add(new Node<String>("Gabriella"));
        linkedStrings.add(new Node<String>("Felipe"));

        linkedStrings.print(linkedStrings.getHead());
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