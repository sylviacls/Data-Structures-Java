
public class IntegerLinkedList {

    private IntegerNode head;
    private int size;

    public void addToFront(Integer value) {
        IntegerNode node = new IntegerNode(value);
        node.setNext(head);
        head = node;
        size++;
    }

    public IntegerNode removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        IntegerNode removedNode = head;
        head = head.getNext();
        size--;
        removedNode.setNext(null);
        return removedNode;
    }

    public void insertSorted(Integer value) {

        // assumption: the list is sorted

        if (head == null || head.getValue() >= value) {
            addToFront(value);
            return;
        }

        // find the insertion point
        IntegerNode current = head.getNext();
        IntegerNode previous = head;
        while (current != null && current.getValue() < value) {
            previous = current;
            current = current.getNext();
        }

        IntegerNode newNode = new IntegerNode(value);
        newNode.setNext(current);
        previous.setNext(newNode);

        size++;

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        IntegerNode current = head;
        System.out.print("HEAD -> ");
        while (current != null) {
            System.out.print(current);
            System.out.print(" -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    class IntegerNode {

        private Integer value;
        private IntegerNode next;
    
        public IntegerNode(Integer value) {
            this.value = value;
        }
    
        public Integer getValue() {
            return value;
        }
    
        public void setValue(Integer value) {
            this.value = value;
        }
    
        public IntegerNode getNext() {
            return next;
        }
    
        public void setNext(IntegerNode next) {
            this.next = next;
        }
    
        public String toString() {
            return value.toString();
        }
    }

    public static void main(String[] args) {

        Integer one = 1;
        Integer two = 2;
        Integer three = 3;
        Integer four = 4;

        IntegerLinkedList list = new IntegerLinkedList();
        list.insertSorted(three);
        list.printList();
        list.insertSorted(two);
        list.printList();
        list.insertSorted(one);
        list.printList();
        list.insertSorted(four);
        list.printList();

    }

}
