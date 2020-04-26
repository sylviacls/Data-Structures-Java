import java.util.EmptyStackException;

/**
 * Generic Stack implementation backed by a custom LinkedList<T>
 * @param <E>
 */
public class LinkedStack<E> {

    private LinkedList<E> stack;

    public LinkedStack() {
        this.stack = new LinkedList<E>();
    }

    /**
     * Adds the item to the top of the stack
     * @param item to be added
     */
    public void push(E item) {
        this.stack.add(item);
    }

    /**
     * Removes the top item on the stack
     * @return the item removed
     */
    public E pop() {
        E itemPopped = this.stack.remove();
        if (itemPopped == null) throw new EmptyStackException();
        return itemPopped;
    }

    /**
     * Return the top item on the stack
     * @return the item located on the top
     */
    public E peek() {
       return this.stack.getHeadData();
    }

    public void print() {
        for (E e : stack) {
            System.out.println(e);
            System.out.println("---");
        }
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<String>();
        stack.push("Sylvia");
        stack.push("Gabriella");
        stack.push("Felipe");
        stack.push("Maria");
        stack.print(); 
        System.out.println("Item removed = "+ stack.pop());
        System.out.println("Item peeked = "+ stack.peek());
        stack.print();
        stack.push("Pedro");
        stack.print();
    }
}