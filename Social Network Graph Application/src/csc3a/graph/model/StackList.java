/**
 * 
 */
package csc3a.graph.model;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 * Implements Stack interface
 *
 */
public class StackList<T> implements IStack<T> {
	
	/** Realizes Doubly Linked List implementation*/
	private DoublyLinkedList<T> stack = null;
	private int                 size  = 0;
	
	/**
	 * Default constructor
	 */
	public StackList() {
		
		stack = new DoublyLinkedList<>();
		
	}
	

	@Override
	public void push(T element) {
		
		stack.addLast(element);
		size++;
		
	}

	@Override
	public T pop() {
		
		T element = stack.lastElement();
		
		if(isEmpty()) {
			
			return null;
		} else {
			
			stack.remove(stack.trailerNode.getPrevNode());
			size--;
			
			return element;
		}
		
	}

	@Override
	public T peek() {
		
		if(isEmpty()) {
			
			return null;
		} else {
			
			return stack.lastElement();
			
		}
		
	}

	@Override
	public boolean isEmpty() {
		
		return size == 0;
	}

	@Override
	public int size() {
		
		return size;
	}
	
	@Override
	public String  toString() {
		
		if(isEmpty()) {
			
			return null;
		} else {
			
			return stack.toString();
		}
	}

}
