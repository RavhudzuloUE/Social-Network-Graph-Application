/**
 * 
 */
package csc3a.graph.model;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 * Doubly Linked List interface
 *
 */
public interface ILinkedList<T> {
	
	public void addFirst(T element);
	public void addBefore(NodeClass<T> node, T element);
	public void addAfter(NodeClass<T> node, T elemnt);
	public void addLast(T element);
	public T         remove(NodeClass<T> nodeRemove);
	public NodeClass<T> search(T elementSearch);
	
	public T firstElement();
	public T lastElement();
	
	public boolean isEmpty();
	public int     size();
	
	public String toString();

}
