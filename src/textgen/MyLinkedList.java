package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		this.size = 0;
		
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) {
		insertNewNodeWithData(element, tail);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
		return getNode(index).data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element) {
		if (index == size) {
			add(element);
			return;
		}
		
		insertNewNodeWithData(element, getNode(index));
	}

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		LLNode<E> currentNode = getNode(index);
		
		currentNode.prev.next = currentNode.next;
		currentNode.next.prev = currentNode.prev;
		size--;
		
		return currentNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Element provided is null");
		}
		
		LLNode<E> node = getNode(index);
		E oldData = node.data;
		node.data = element;
		return oldData;
	}
	
	public String toString() {
		String result = "LinkedList content:\n";
		LLNode<E> currentNode = head.next;
		int i = 0;
		while (currentNode.data != null) {
			result = result + "index " + i + " / node content: " + currentNode + "\n";
			i++;
			currentNode = currentNode.next;
		}
		
		return result;
	}
	
	private void insertNewNodeWithData(E element, LLNode<E> nextNode) {
		if (element == null) {
			throw new NullPointerException("Element provided is null");
		}
		
		LLNode<E> insertedNode = new LLNode<E>(element);
		
		insertedNode.prev = nextNode.prev;		
		insertedNode.next = nextNode;			
		nextNode.prev = insertedNode;			
		insertedNode.prev.next = insertedNode;
		
		size++;
	}
	
	private LLNode<E> getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index " + index + " is out of the bound");
		}
		
		LLNode<E> currentNode = null;
		if (index > size / 2) {
			currentNode = tail.prev;
			for (int i = size - 1; i > index; i--) {
				currentNode = currentNode.prev;
			}
		} else {
			currentNode = head.next;
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.next;
			}
		}
		
		return currentNode;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public String toString() {
		return "Node content :" + data.toString();
	}

}