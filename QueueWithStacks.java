import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implements a Queue using two Stacks
 */
public class QueueWithStacks<E> {

    private Deque<E> stack1;
    private Deque<E> stack2;

    public QueueWithStacks() {
        this.stack1 = new ArrayDeque<E>();
        this.stack2 = new ArrayDeque<E>();
    }

    /**
     * Add an item at beggining of the queue
     * @param item
     */
    public void add(E item) {
        this.stack1.push(item);
    }

    /**
     * Remove an element from the queue.
     * Before removing it we have to fill the stack2 with the values stores in stack1
     * and then remove the element from the stack2.
     * @return the element removed
     */
    public E remove() {
        if(this.stack2.isEmpty()) {
           int size = stack1.size();
           for (int i = 1; i <= size; i++) {
             stack2.push(stack1.pop());  
           }
        }
        return stack2.pop();
    }

    /**
     * Auxiliar method to print the Queue
     * We have to iterate in descending order for Stack1 
     * And in ascending order for Stack2
     */
    public void print() {
        if(this.stack2.isEmpty()) {
            stack1.descendingIterator().forEachRemaining(System.out::println);
        } else {
            stack2.iterator().forEachRemaining(System.out::println);
            stack1.descendingIterator().forEachRemaining(System.out::println);
        }
    }
    public static void main(String[] args) {
        QueueWithStacks<String> queue = new QueueWithStacks<String>();
       
        queue.add("Sylvia");
        queue.add("Gabriella");
        queue.add("Felipe");
        queue.print();

        System.out.println("Item removed :" + queue.remove());
        queue.print();

        System.out.println("Item removed :" + queue.remove());
        queue.print();

        queue.add("Pedro");
        queue.add("Danilo");
        queue.print();

        System.out.println("Item removed :" + queue.remove());
        queue.print();
    }
}