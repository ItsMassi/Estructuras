package TDALista;


public class Nodo<E> implements Position<E> {
	protected E elemento;
	protected Nodo<E> siguiente;
	
	
	public Nodo(E item, Nodo<E> sig){
		elemento = item;
		siguiente = sig;
	}
	
	public Nodo(E item){
		this(item,null);
	}
	
	public void setElemento(E elemento){
		this.elemento = elemento;
	}
	
	public void setSiguiente(Nodo<E> siguiente){
		this.siguiente = siguiente;
	}
	
	public E element(){
		return elemento;
	}
	
	public Nodo<E> getSiguiente(){
		return siguiente;
	}
	
	public Nodo<E> clone() {
		return new Nodo<E>(element(),getSiguiente());
	}
}
