package TDALista;

public class DNodo<E> implements Position<E> {
	protected E elemento;
	protected DNodo<E> siguiente,anterior;
	
	
	public DNodo(E item, DNodo<E> sig, DNodo<E> ant){
		elemento = item;
		siguiente = sig;
		anterior=ant;
	}
	
	public DNodo(E item){
		this(item,null,null);
	}
	
	public void setElemento(E elemento){
		this.elemento = elemento;
	}
	
	public void setSiguiente(DNodo<E> siguiente){
		this.siguiente = siguiente;
	}
	
	public void setAnterior(DNodo<E> anterior){
		this.anterior = anterior;
	}
	public E element(){
		return elemento;
	}
	
	public DNodo<E> getSiguiente(){
		return siguiente;
	}
	public DNodo<E> getAnterior(){
		return anterior;
	}
	
}

