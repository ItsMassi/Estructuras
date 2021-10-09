package TDACola;
//import TDAPila.*;
public class ColaConEnlaces<E> implements Queue<E> {	
	protected Nodo<E> head,tail;
	protected int tama�o;
	
	public ColaConEnlaces() {
		head=null;
		tail=null;
		tama�o=0;
	}
	
	public void enqueue(E element) {
		Nodo <E> nodo= new Nodo<E>(element,null);
		//nodo.setElemento(element);
		//nodo.setSiguiente(null);
		if (isEmpty()) {
			head=nodo;
		}
		else {
			tail.setSiguiente(nodo);
		}
		tail=nodo;
		tama�o++;
	}
	
	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Error, la pila est� vac�a");
		}
		else {
			E aux=head.getElemento();
			head=head.getSiguiente();
			tama�o--;
			if (tama�o==0) {// si antes habia un elemento, ese era igual a head y tail 
				tail=null;//entonces pongo tail=null
			}
			return aux;
		}
	}
	
	public boolean isEmpty() {
		return tama�o==0;
	}
	
	public int size() {
		return tama�o;
	}
	
	public E front() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Error, la cola est� vac�a");
		}
		else {
			return head.getElemento();
		}
	}
	
	public void invertir() throws EmptyQueueException { 
		if (isEmpty()) {
			throw new EmptyQueueException("Error, la pila esta vacia");
		}
		else {
			invertirAux(this);
		}
	}
	
	private void invertirAux(ColaConEnlaces<E> c) throws EmptyQueueException { 
		if (isEmpty()) {
			throw new EmptyQueueException("Error, la pila esta vacia");
		}
		else {//una cola invertida es una cola invertida sin su frente, con el frente como ultimo elemento
			E aux=dequeue();
			invertirAux(this);
			enqueue(aux);
		}
	}
	
	public String toString() {
		String s="";
		try {
			
			while (!isEmpty()) {
				s=s+" - "+head.toString();
				dequeue();
			}
			return s;
		}
		catch (EmptyQueueException e) {}
		return s;
		
	}
}
