/**
 * 
 */
package csc3a.graph.model;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 * Stack interface for first in last out operation
 */
public interface IStack<T> {
	
	public void push(T element);
	public T    pop();
	
	public T    peek();
	
	public boolean isEmpty();
	public int     size();
	public String  toString();

}
