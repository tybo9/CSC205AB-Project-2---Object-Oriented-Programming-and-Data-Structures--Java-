/**
 * Stack.java
 * 
 * This stack class represent a last-in-first-out. Large collection of objects such as stack and pop(), push(), peek() it gets manipulated randomly. 
 * For Pop(), and push() they both get defined to insert new item & remove the item. So when it insert an item, the insert is clear. 
 * The Stack and the data structure to keep track of whether or not a cell has been “visited”.
 * 
 * Ayoub Rammo
 * 
 * CSC205
 * 
 */
import java.io.Serializable;
import java.util.Arrays;
import java.util.*;

public class Stack<E> implements Serializable
{
    //Instance variables
    private int head; //this is same as size
    ArrayList<E> RM = new ArrayList<E> ();
    //Methods
    /**
     * Adding the element to the top of the stack.
     * Resizes the array if needed.
     *
     * @param element it is an item to be pushed
     * @return E  The item that was pushed
     */
    public E push(E element)
    {
        RM.add(head, element);
        head++;
        return element;
    }

    /**
     * This method removes the object at the top of this stack and returns that object as the value of this function.
     * 
     * @param top of the stack removing and returning the element
     *
     * @return the value that got popped
     */
    public E pop()
    {
        //checks the stack is not empty
        if (isEmpty())
            throw new EmptyStackException();
        //return the popped object
        E arrayElement=RM.get(head-1);
        RM.remove(head-1);
        head--;
        return arrayElement;
    }

    /**
     * what is doing in this part is to look at it, and not removing the element of the stack
     * 
     *
     * @return the value at the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    public E peek()
    {
        if(isEmpty()) //checking
        {
            throw new EmptyStackException(); //I named this exactly as Java result
        }

        return RM.get(head-1);
    }

    /**
     * Decide if the stack is empty. This method tests if this stack is empty.
     *
     * @return True if the stack is empty, otherwise is false
     */
    public boolean isEmpty()
    {
        return size () == 0;
    }

    /**
     * The size of the stack
     * 
     */
    public int size()
    {
        return head;
    }

}