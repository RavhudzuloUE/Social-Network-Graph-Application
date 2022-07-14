/**
 * 
 */
package csc3a.graph.model;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 * Queue Interface for first in first out operation
 *
 */
public interface IQueue<T> {
	
	public void enqueue(T element);
	public T    dequeue();
	
	public T    first();
	
	public boolean isEmpty();
	public int     size();
	public String  toString();

}
