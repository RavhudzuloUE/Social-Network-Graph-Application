
package csc3a.graph.model;


/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 * Implements Queue ADT
 *
 */
public class QueueList<T> implements IQueue<T>{
	
	/** Realizes Doubly Linked List implementation*/
	private DoublyLinkedList<T> queue = null;
	private int                 size  = 0;
	
	/**
	 * Default Constructor
	 */
	public QueueList() {
		
		queue = new DoublyLinkedList<>();
		
	}

	@Override
	public void enqueue(T element) {
		
		queue.addLast(element);
		size++;
		
	}

	@Override
	public T dequeue() {
		
		T element = queue.firstElement();
		
		if(isEmpty()) {
		
			return null;
		} else {
			
			queue.remove(queue.headerNode.getNextNode());
			size--;
			
			return element;
		}
	}

	@Override
	public T first() {
		
		if(isEmpty()) {
			
			return null;
		} else {
		
			return queue.firstElement();
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
			
			return queue.toString();
		}
	}	

}
