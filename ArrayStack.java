import java.lang.reflect.Array;
import java.util.EmptyStackException;

public class ArrayStack<E> {
    private E[] stack;
    private int top; // next position avaiable in stack
    private Class classType;


    public ArrayStack(Class<E> classType) {
        this.stack = (E[]) Array.newInstance(classType, 2);
        this.classType = classType;
    }

    /**
     * Checks if the array is full
     * @return
     */
    public boolean isFull() {
        return (this.top == this.stack.length);
    }

    /**
     * Adds the item to the top of the stack
     * @param item to be added
     */
    public void push(E item) {
        if (!isFull()) {
            this.stack[top] = item;
            top++;
        } else {
            E[] newArray = (E[]) Array.newInstance(this.classType, this.stack.length*2);
            System.arraycopy(stack, 0, newArray, 0, this.stack.length);
            this.stack = newArray;

            this.stack[top] = item;
            top++;
        }
    }

    /**
     * Checks if the stack is empty
     * @return 
     */
    public boolean isEmpty() {
        return (this.top == 0);
    }

    /**
     * Removes the top item on the stack
     * @return the item removed
     */
    public E pop() {
        if(isEmpty()) throw new EmptyStackException();

        E itemPopped = this.stack[top-1];
        this.stack[top-1] = null;
        top--;
        return itemPopped;

    }

    /**
     * Return the top item on the stack
     * @return the item located on the top
     */
    public E peek() {
        if(isEmpty()) throw new EmptyStackException();

        return this.stack[top-1];
    }

    /**
     * Return the size of the stack based on the top atributte
     * @return the size
     */
    public int size() {
        return this.top;
    }

    /**
     * 
     */
    public void print() {
        for(int i = this.top-1; i >= 0; i--) {
            System.out.println(this.stack[i]);
            System.out.println("---");
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(String.class);
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