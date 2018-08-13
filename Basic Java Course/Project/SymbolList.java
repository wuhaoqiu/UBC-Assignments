package P5;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SymbolList {//prevent from duplicate symbols
	private int size=0;
	private Node<Symbol> head=null,tail=null;
	
	public boolean contains(String name){//check whether there already store the same symbol
		Node<Symbol> n=head;//search from the beginning
		while(n!=null){
			if(n.element.getName().equals(name)){
				return true;
			}//end of if
			n=n.next;
		}//end of while
		return false;
	}
		
	public void add(Symbol symbol){
		Node<Symbol> n =new Node<Symbol>(symbol);
		if(size==0){
			head=tail=n;
		}else{
			if(!contains(symbol.getName())){
				tail.next=n;
				tail=n;
			}	
		}	
		size++;
	}//end of add
	
	public int getLocation(String name){
		Node<Symbol> n=head;//search from the begining
		while(n!=null){
			if(n.element.getName().equals(name)){
				return n.element.getLocation();
			}//end of if
			n=n.next;
		}//end of while
		throw new NoSuchElementException();
	}
	
	public Integer getValue(String name){
		Node<Symbol> n=head;//search from the begining
		while(n!=null){
			if(n.element.getName().equals(name)){
				return n.element.getValue();
			}//end of if
			n=n.next;
		}//end of while
		throw new NoSuchElementException();
	}
	
	public Integer getValue(int location){
		Node<Symbol> n=head;//search from the begining
		while(n!=null){
			if(n.element.getLocation()==location){
				return n.element.getValue();
			}//end of if
			n=n.next;
		}//end of while
		throw new NoSuchElementException();
	}
	
	public String toString(){
		Node<Symbol> n=head;
		String s="";
		while(n!=null){
			s+=(n.element.getName()+" , "+n.element.getLocation()+" , "+n.element.getValue()+"\n");
			n=n.next;
		}
		return s;
	}
	
	public Iterator<Symbol> iterator(){
		return new MyIterator();
	}
	
	class MyIterator implements Iterator<Symbol>{
		private Node<Symbol> current = head;
		public boolean hasNext() {
		return (current.next != null);
		}
		public Symbol next() {
		Symbol tmp = current.element;
		current = current.next;
		return tmp;
		}
	}
	
	private class Node<Symbol>{
		Symbol element;
		Node<Symbol> next;
		public Node(Symbol e){
			element=e;
		}
	}//end of node class

}
