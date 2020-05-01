import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class ArrayQueue<E> {

    private E[] queue;
    private int front;
    private int back;
    private boolean resize; // stores the user option for resizing the queue when it gets full

    @SuppressWarnings("unchecked")
    public ArrayQueue(Class<E> classType, int capacity, boolean resize) {
        this.queue = (E[]) Array.newInstance(classType, capacity);
        this.resize = resize;
        this.front = -1;
        this.back = -1;
    }

    /**
     * Add an element to the back of the queue
     */
    public void add(E item) {
        if(isFull()) {
            if(this.resize == false) {
                System.out.println("The queue is Full!");
                return;
            } else {
                Class<?> classType = this.queue.getClass().getComponentType();
                @SuppressWarnings("unchecked")
                E[] newQueue = (E[]) Array.newInstance(classType, queue.length*2);
                System.arraycopy(this.queue, 0, newQueue, 0, queue.length);
                this.queue = newQueue;
                System.out.println("The queue has been resized! Element added = "+ item.toString());
            }
        }
        if(this.front == -1 ) {
            this.front=0;
            this.back=0;
        } else {
            this.back++;
        }
        this.queue[this.back] = item;

    }

    /**
     * Check if the queue is full
     * @return
     */
    public boolean isFull() {
        return (this.back == queue.length-1);
    }

    /**
     * Check if the queue is empty
     * @return
     */
    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        return this.back - this.front;
    }

    /**
     * Remove an element from the front of the queue
     * @return the removed element
     */

    public E remove() {
        if (isEmpty()) throw new NoSuchElementException();
        
        E itemRemoved = this.queue[this.front];
        this.queue[this.front] = null;
        this.front++;

        //optimization 
        if (size() == 0) {
            this.front = -1;
            this.back = -1;
        }
        
        return itemRemoved;
    }
    /**
     * Peek the first element of the queueu without removing it
     * @return the first element
     */

    public E peek() {
        if (isEmpty()) throw new NoSuchElementException();

        return this.queue[this.front];
    }

    public void print() {
        for (int i = this.front; i <= this.back; i++) {
            System.out.print(this.queue[i] + "==>");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<String>(String.class, 2, true);
        queue.add("Sylvia");
        queue.add("Gabriella");
        
        queue.add("Pedro");

        queue.print();
       /* queue.add("Danilo");
        queue.print();
     //   System.out.println("Peeked " + queue.peek());
        queue.remove();
        queue.remove();
      //  queue.remove();
       // queue.remove();
 //       queue.print();
     //   queue.peek();
       // queue.print();
       queue.print();
        queue.add("Gabriella");
        queue.add("Pedro");
        queue.add("Danilo");
        queue.add("Carol");
        queue.print(); */
    }
}