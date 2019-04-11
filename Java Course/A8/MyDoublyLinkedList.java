package A8;



public class MyDoublyLinkedList<E> {
	private int size;
	private Node<E> head, tail;

	// Constructors
	public MyDoublyLinkedList() {
		head = tail = null;
	}

	public MyDoublyLinkedList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}

	// Methods

	// *** ADDING ***
	public void add(E e) {
		addLast(e);
	}

	public void addFirst(E e) { /** add code to set 'previous' [1 mark] */
		Node<E> newNode = new Node<E>(e); // Create a new node
		if (tail == null) // if empty list
			head = tail = newNode; // new node is the only node in list
		else {
			newNode.next = head; // link the new node with the head
			newNode.previous=null;
			head = newNode; // head points to the new node
		}
		size++;
	}

	public void addLast(E e) { /** add code to set 'previous' [1 mark] */
		Node<E> newNode = new Node<E>(e); // Create a new for element e
		if (tail == null) // if empty list
			head = tail = newNode; // new node is the only node in list
		else {
			tail.next = newNode; // Link the new with the last node
			newNode.previous=tail;
			tail = tail.next; // tail now points to the last node
		}
		size++;
	}

	public void add(int index, E e) {/**
										 * add code to set 'previous' & to improve
										 * performance [2 marks]
										 */
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException(); // according to Java doc.
		else if (index == 0)
			addFirst(e);
		else if (index == size)
			addLast(e);
		else {
			if(index<size/2){//same sroty, if index > size/2, 
			Node<E> newNode = new Node<E>(e);
			Node<E> current = head; // ]
			for (int i = 1; i < index; i++) // ]- get a reference to index-1
				current = current.next; // ]
			newNode.next = current.next; // Link the new node to element @ index
			current.next = newNode; // Link element @ index-1 to newNode
			size++;}//end of inner if
			else{
				Node<E> newNode=new Node<E>(e);
				Node<E> current=tail;
				for(int i=size-1;i>index;i++){
					current=current.previous;
				}
				newNode.next=current.next;
				current.next=newNode;
				size++;
			}
		}
	}

	// *** REMOVING ***
	public E removeFirst() { /** add code to set 'previous' [1 mark] */
		if (size == 0)
			return null;
		else {
			Node<E> temp = head; // element will be returned
			head = head.next;
			head.previous=null;
			size--;
			if (head == null) // if list becomes empty
				tail = null;
			return temp.element; // return the removed element
		}
	}

	public E removeLast() { /** improve performance using 'previous' [1 mark] */
		if (size == 0)
			return null;
		else  {
			Node<E> temp=tail;
			tail=tail.previous;
			tail.next=null;
			size--;
			if (tail == null) // if list becomes empty
				head = null;
			return temp.element;
			} // return the removed element
			
	}

	public E remove(int index) { /** add code to set 'previous' [2 marks] */
		if (index < 0 || index >= size)
			return null;
		else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else {
			Node<E> previous = head;
			for (int i = 1; i < index; i++)
				previous = previous.next;
			Node<E> current = previous.next;
			current.next.previous=previous;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}

	public boolean remove(E e) {
		if (indexOf(e) >= 0) { // if the element exists
			remove(indexOf(e)); // call the other remove method
			return true;
		} else
			return false;
	}

	public void clear() {
		size = 0;
		head = tail = null;
	}

	// *** GETTING ***
	public E getFirst() {
		if (size == 0)
			return null;
		else
			return head.element;
	}

	public E getLast() {
		if (size == 0)
			return null;
		else
			return tail.element;
	}

	public E get(
			int index) { /** improve performance using 'previous' [1 mark] */
		if (index < 0 || index >= size)
			return null;
		else if (index == 0)
			return getFirst();
		else if (index == size - 1)
			return getLast();
		else {
			if(index<size/2){
			Node<E> current = head; // ]
			for (int i = 0; i < index; i++) // ]- get a reference to node @
											// index
				current = current.next; // ]
			return current.element;}
			else{
				Node<E> current=tail;
				for(int i=size-1;i>index;i--){
					current=current.previous;
				}
				return current.element;
			}
		}
	}

	// *** SETTING ***
	public E set(int index,E e) { /** improve performance using 'previous' [1 mark] */
		if (index < 0 || index > size - 1)
			return null;
		Node<E> current=null;
		if(index<size/2){
		current = head;
		for (int i = 0; i < index; i++)
			current = current.next;
		E temp = current.element;
		current.element = e;
		return temp;}
		else{
			current=tail;
			for(int i=size-1;i>index;i--){
				current=current.previous;
			}
			E temp=current.element;
			current.element=e;
			return temp;
		}
	}

	// *** TOSTRING ***
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null)
				result.append(", "); // Separate two elements with a comma
			else
				result.append("]"); // Insert the closing ] in the string
		}
		return result.toString();
	}

	public String toReversedString() {/**
										 * write code to return a string
										 * representing the list from right to
										 * left [3 marks]
										 */
		StringBuilder result = new StringBuilder("[");
		Node<E> current = tail;
		for (int i = size-1; i >-1 ; i--) {
			result.append(current.element);
			current = current.previous;
			if (current != null)
				result.append(", "); // Separate two elements with a comma
			else
				result.append("]"); // Insert the closing ] in the string
		}
		return result.toString();
	}

	// *** CHECKING ***
	public int size() {
		return size;
	}

	public boolean contains(E e) {
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			if (current.element.equals(e))
				return true;
			current = current.next;
		}
		return false;
	}

	public int indexOf(E e) {
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			if (current.element.equals(e))
				return i;
			current = current.next;
		}
		return -1;
	}

	public int lastIndexOf(E e) { /** improve performance using 'previous' [3 marks] */
		int lastIndex = -1;//check from the tail to the begining, and return when firstly encounter the e
		Node<E> current = tail;
		for (int i = size - 1; i >0; i--) {
			if (current.element.equals(e))
				return i;
			current = current.previous;
		}
		return lastIndex;

	}

	// *** HELPER METHODS ***
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException("Index: " + idx + ", Size: " + size);
	}

	private Node<E> getNodeAt(
			int index) { /**
							 * write code for this method to return a reference to
							 * a node at a given index [3 marks]
							 */
		checkIndex(index);
		Node<E> current = null;
		if (index == 0) {
			current = head;
			return current;
		} else if (index == size - 1) {
			current = tail;
			return current;
		} else {
			if (index > size / 2) {// if index of the required element is
									// greater than the middle of the list,
									// using previous, retrieve from the last
				current = tail;
				for (int i = size - 1; i > index; i--) {
					current = current.previous;
				} // end of for
				return current;
			} else {
				current = head;
				for (int i = 0; i < index; i++) {
					current = current.next;
				}
				return current;
			}//end of inner if
		}//end of outer if

	}

	// *** INNER NODE CLASS ***
	private static class Node<E> {
		/** add code to consider 'previous' [1 mark] */
		E element;
		Node<E> next;
		Node<E> previous;// adding one more reference to previous element

		public Node(E e) {
			element = e;
		}
	}
}