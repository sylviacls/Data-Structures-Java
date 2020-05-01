import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class CircularArrayQueue<E> {

    private E[] queue;
    private int front;
    private int back;
    private boolean resize; // stores the user option for resizing the queue when it gets full

    @SuppressWarnings("unchecked")
    public CircularArrayQueue(Class<E> classType, int capacity, boolean resize) {
        this.queue = (E[]) Array.newInstance(classType, capacity);
        this.resize = resize;
        this.front = -1;
        this.back = -1;
    }

    /**
     * Add an element to the back of the queue
     */
    public void add(E item) {
        if (isFull()) {
            if(this.resize == false) {
                System.out.println("The queue is Full! It's not possible to add " + item.toString());
                return;
            } else {
                Class<?> classType = this.queue.getClass().getComponentType();
                int numItem = this.queue.length;
            
                @SuppressWarnings("unchecked")
                E[] newQueue = (E[]) Array.newInstance(classType, queue.length * 2);

                if(this.front >= this.back) { // the queue is wrapped
                    System.arraycopy(this.queue, this.front, newQueue, 0, queue.length - this.front);
                    System.arraycopy(this.queue, 0, newQueue, queue.length - this.front, this.back);
                } else {
                    System.arraycopy(this.queue, 0, newQueue, 0, queue.length);
                }

                this.queue = newQueue;
                this.front = 0;
                this.back = numItem-1;
                System.out.println("The queue has been resized! Element added = "+ item.toString());
            }
        }
        
        //first element
        if(this.front == -1) {
            this.front = 0;
            this.back = 0;
        } 
        // reaching the end of the queue and it has space at te beginning
        else if (this.front != 0 && this.back == this.queue.length -1) {
             this.back = 0;
        //common case
        } else { 
            this.back++;
        }
        this.queue[this.back] = item;
        
    }

    /**
     * Check if the queue is at its maximum capacity to be a circular queue We have
     * to guarantee that we have space when resizing it
     * 
     * @return
     */
    public boolean isFull() {
        if(this.front == 0 && this.back == this.queue.length-1) return true;

        if(this.back == this.front - 1) return true;

        return false;
    }

    /**
     * Check if the queue is empty
     * 
     * @return
     */
    public boolean isEmpty() {
        return (this.front == -1 && this.back ==- 1);
    }

    /**
     * Remove an element from the front of the queue
     * 
     * @return the removed element
     */

    public E remove() {
        if (isEmpty())
            throw new NoSuchElementException();

        E itemRemoved = this.queue[this.front];
        this.queue[this.front] = null;

        // optimization.  If queueu has only one element, so we reset the queue after deleting it
        if (this.front == this.back) { 
            this.front = -1;
            this.back = -1;
        } else if(this.front == this.queue.length -1 ) { //front is the maximum value
            this.front = 0;
        } else {
            this.front++;
        }

        return itemRemoved;
    }

    /**
     * Peek the first element of the queueu without removing it
     * 
     * @return the first element
     */

    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();

        return this.queue[this.front];
    }

    public void print() {

        if (isEmpty()) {
            System.out.println("Empty Queue");
        } else {
            // check if the queue isn't wrapped
            if (this.front <= this.back) {
                for (int i = this.front; i <= this.back; i++) {
                    System.out.print(this.queue[i] + " " + i + " ==> ");
                }
                System.out.println("");           
            } else {
                for (int i = this.front; i < queue.length; i++) {
                    System.out.print(this.queue[i]  + " " + i + " ==> ");
                }
                for (int i = 0; i <= this.back; i++) {
                    System.out.print(this.queue[i] + " " + i + " ==> ");
                }
                System.out.println("");   
            }
    }

    }

    public static void main(String[] args) {
        CircularArrayQueue<String> queue = new CircularArrayQueue<String>(String.class, 4,false);
        queue.add("Sylvia");
        queue.add("Gabriella");
        queue.add("Pedro");
        queue.add("Danilo");
        queue.print();
        System.out.println("Peeked " + queue.peek());
        queue.print();
        queue.add("Carol");
        queue.print();
        queue.remove();    
        queue.remove();
        queue.remove();
        queue.print();
        queue.remove();
        queue.print();
        queue.add("Alanis");
        queue.add("Maria");
        queue.print();
        queue.add("Manoel");
        queue.add("Marisa");
        queue.print();  
        queue.add("Felipe"); 
        queue.print();  
    }
}