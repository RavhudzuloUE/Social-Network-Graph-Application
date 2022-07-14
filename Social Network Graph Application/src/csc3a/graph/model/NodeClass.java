/**
 * 
 */
package csc3a.graph.model;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 *
 * Class to store the references and element
 */
public class NodeClass<T> {
	
	private NodeClass<T> nextNode;
	private NodeClass<T> prevNode;
	private T element;
	
	/**
	 * Parameterized constructor
	 * 
	 * @param prevNode   reference to the previous node
	 * @param element    contain the element
	 * @param nextNode   reference to the next element
	 */
	public NodeClass(NodeClass<T> prevNode, T element, NodeClass<T> nextNode) {
		
		this.prevNode = prevNode;
		this.element  = element;
		this.nextNode = nextNode;
		
	}

	/**
	 * Access the reference of the next node
	 *  
	 * @return the nextNode
	 */
	public NodeClass<T> getNextNode() {
		
		return nextNode;
	}

	/**
	 * set the reference of the next node
	 * 
	 * @param nextNode the nextNode to set
	 */
	public void setNextNode(NodeClass<T> nextNode) {
		
		this.nextNode = nextNode;
	}

	/**
	 * Access the reference of the previous node
	 * 
	 * @return the prevNode
	 */
	public NodeClass<T> getPrevNode() {
		return prevNode;
	}

	/**
	 * Set the reference to the next node
	 * 
	 * @param prevNode the prevNode to set
	 */
	public void setPrevNode(NodeClass<T> prevNode) {
		this.prevNode = prevNode;
	}

	/**
	 * Get the store element
	 * 
	 * @return the element
	 */
	public T getElement() {
		return element;
	}

	/**
	 * set the element ot be stored
	 * 
	 * @param element the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}
	
	/**
	 * toString return the string representation of an object
	 */
	public String toString() {
		
		String results = "";
		
		results = "<" + getElement() + ">";
		
		return results;		
	}

}
