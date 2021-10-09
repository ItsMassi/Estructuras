package TDAPila;

/**
 * Define los datos y operaciones aplicables sobre una pila con enlaces.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos a almacenar en la pila con enlaces.
 */
public class Pila_con_enlaces<E> implements Stack<E> {
	/**
	 * Nodo tope de la pila, que corresponde al último elemento apilado.
	 */
	protected Nodo<E> head;
	/**
	 * Tamaño de la pila.
	 */
	protected int tamaño;
	
	/**
	 * Inicializa una pila con enlaces con head nulo y tamaño 0.
	 */
	public Pila_con_enlaces () {
		head=null;
		tamaño=0;
	}
	
	@Override
	public int size() {
		return tamaño;
	}
	
	@Override
	public boolean isEmpty() {
		return tamaño==0;
	}
	
	@Override
	public E top() throws EmptyStackException {
		if (tamaño==0) {
			throw new EmptyStackException("Error, la pila está vacía.");
		}
		else {
			return head.getElemento();
		}
	}
	
	@Override
	public void push(E item) {
		Nodo <E> aux= new Nodo<E> (item,head);
		head=aux;
		tamaño++;
	}
	
	@Override
	public E pop() throws EmptyStackException {
		if (tamaño==0) {
			throw new EmptyStackException("Error, la pila está vacía.");
		}
		else {
			E aux=head.getElemento();
			head=head.getSiguiente();
			tamaño--;
			return aux;
		}
	}
}