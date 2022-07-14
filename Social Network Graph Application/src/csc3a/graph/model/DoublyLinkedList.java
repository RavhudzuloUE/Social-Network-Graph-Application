package csc3a.graph.model;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 * Implementation of a linked list interface 
 *
 */
public class DoublyLinkedList<T> implements ILinkedList<T> {
	
	/** Dummy first node */
	public NodeClass<T> headerNode; /** Dummy Last node*/
	public NodeClass<T> trailerNode;
	private int       size = 0;
	
	/**
	 * Default Constructor
	 */
	public DoublyLinkedList() {
		
		headerNode  = new NodeClass<>(null, null, null);
		trailerNode = new NodeClass<>(headerNode, null, null);
		headerNode.setNextNode(trailerNode);
		
	}
	
	@Override
	public void addFirst(T element) {
		
		addAfter(headerNode, element);
		
	}


	@Override
	public void addAfter(NodeClass<T> node, T element) {
		
		NodeClass<T> currentNode = new NodeClass<>(null, null, null);
		             currentNode.setElement(element);
		             node.getNextNode().setPrevNode(currentNode);		             
		             currentNode.setNextNode(node.getNextNode());
		             node.setNextNode(currentNode);
		             currentNode.setPrevNode(node);
		             
		             size++;
		             
	}


	@Override
	public void addBefore(NodeClass<T> node, T element) {
		
		NodeClass<T> currentNode = new NodeClass<>(null, null, null);
		             currentNode.setElement(element);
		             node.getPrevNode().setNextNode(currentNode);
		             currentNode.setPrevNode(node.getPrevNode());
		             node.setPrevNode(currentNode);
		             currentNode.setNextNode(node);
		             
		             size++;
		             
	}


	@Override
	public void addLast(T element) {
		
		addBefore(trailerNode, element); // Add before method
	}


	@Override
	public T remove(NodeClass<T> nodeRemove) {
		
		if(isEmpty() || nodeRemove == null) {
			
			return null;
		} else {
			
			T element = nodeRemove.getElement();
			
			nodeRemove.getNextNode().setPrevNode(nodeRemove.getPrevNode());
			nodeRemove.getPrevNode().setNextNode(nodeRemove.getNextNode());
			nodeRemove.setPrevNode(null);
			nodeRemove.setNextNode(null);
			nodeRemove.setElement(null);
			size--;
			
			return element;
			
		}

	}


	@Override
	public NodeClass<T> search(T elementSearch) {
		
		if(isEmpty()) {
			
			return null;
		} else {
			
			NodeClass<T> currentNode = headerNode.getNextNode();
			
			while(currentNode != trailerNode) {
				
				if(currentNode.getElement().equals(elementSearch)) {
					
					return currentNode;
				}
				
				currentNode = currentNode.getNextNode();
				
			}
			
			return null;
		
		}
	}


	@Override
	public T firstElement() {
		
		if(isEmpty()) {
			
			return null;
			
		} else {
			
			return headerNode.getNextNode().getElement();
			
		}
	}

	@Override
	public T lastElement() {
		
		if(isEmpty()) {
			
			return null;
			
		} else {
						
			return trailerNode.getPrevNode().getElement();
		}
		
	}

	@Override
	public boolean isEmpty() {
		
		return (headerNode.getNextNode() == trailerNode);  // Check if the there is no node in between
	}

	@Override
	public int size() {
		
		return size;
	}
	
	@Override
	public String toString() {
		
		if(isEmpty()) {
			
			return null;
		} else {
			
			String results = "";
			
			NodeClass<T>  currentNode = headerNode.getNextNode();
			
			while(currentNode.getNextNode() != null) {
				
				results += currentNode.getElement() + " ";	
				
				currentNode = currentNode.getNextNode();
				
			}
			
			return results;
		}
		
	}

}
