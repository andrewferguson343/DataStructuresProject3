/**
   A class of bags whose entries are stored in a chain of linked nodes.
	The bag is never full.
*/

public class LinkedBag<T> implements BagInterface<T>{



	private class Node 
	{
		private T    data; // Entry in bag
		private Node next; // Link to next node
	
		private Node(T dataPortion)
		{
			this(dataPortion, null);	
		} // end constructor
		
		private Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;	
		} // end constructor
	} // end Node

	
	private Node firstNode;       // Reference to first node
	private int numberOfEntries;

	// Default constructor
	public LinkedBag() 
	{
		
		
	} // end default constructor
	
	@Override
	public int getCurrentSize() {
	  
	  int count = 0;
	  Node currentNode = firstNode;
	  while(currentNode != null )
	  {
	    count++;
	    currentNode = currentNode.next;
	  }
		return count;
	}

	@Override
	public boolean isEmpty() {
		if(firstNode == null)
		  return true;
		return false;
	}

	@Override
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry, firstNode);
		firstNode = newNode;
		numberOfEntries++;
		return true;
	}

	@SuppressWarnings("unchecked")
  @Override
	public T remove() {
		if(isEmpty()){
			return null;
		}			
		T buffer = firstNode.data;
		firstNode = firstNode.next;
		numberOfEntries--; 
		return buffer;				
	}

 	// Locates a given entry within this bag.
	// Returns a reference to the node containing the entry, if located,
	// or null otherwise.
	private Node getReferenceTo(T anEntry)
	{
		Node currentNode; 
		
		for (currentNode = firstNode;currentNode != null; currentNode = currentNode.next)
		{
			if (anEntry==currentNode.data)
				return currentNode;
		} // end while
		return currentNode;
	} // end getReferenceTo

	@Override
	public boolean remove(T anEntry) {
		//test to see if the bag is empty
		if(isEmpty()) {
			return false;
		}
		//test to see if entry is in the bag
		Node temp = getReferenceTo(anEntry);
		if(temp == null) {
			return false;		
		}
		//remove the entry
		temp.data = firstNode.data;
		firstNode = firstNode.next;
		numberOfEntries--;
		if(contains(anEntry))
		{
		  return remove(anEntry);
		}
		return true;
	}

	@Override
	public void clear() {	
		firstNode  = null;
		numberOfEntries = 0;
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		Node currentNode = firstNode;
		int count = 0;
		while(currentNode != null)
		{
		  if(currentNode.data == anEntry)
		  {
		    count++;
		  }
		  currentNode = currentNode.next;
		}
		return count;
	}

	@Override
	public boolean contains(T anEntry) {
		Node currentNode = firstNode;
		boolean foundFlag = false;
		while (currentNode != null && foundFlag == false)
		{
			if(anEntry.equals(currentNode.data))
			{
				foundFlag = true;
			}
			else
			{
				currentNode = currentNode.next;
			}
		}
		return foundFlag;
	}

	@Override
	public T[] toArray() {
		T[] temp = (T[])new Object[numberOfEntries];
		Node current = firstNode;		
		for(int i = 0; i < temp.length; i++ ) {
			temp[i] = current.data;
			current = current.next;
		}
		return temp;		
	}
	
}
