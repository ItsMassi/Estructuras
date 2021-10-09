package TDAPila;

/**
 * Define los datos y operaciones aplicables sobre una pila con enlaces.
 * @author Juan Ignacio S�nchez.
 * @param <E> Tipo de dato de los elementos a almacenar en la pila con enlaces.
 */
public class Pila_con_enlaces<E> implements Stack<E> {
	/**
	 * Nodo tope de la pila, que corresponde al �ltimo elemento apilado.
	 */
	protected Nodo<E> head;
	/**
	 * Tama�o de la pila.
	 */
	protected int tama�o;
	
	/**
	 * Inicializa una pila con enlaces con head nulo y tama�o 0.
	 */
	public Pila_con_enlaces () {
		head=null;
		tama�o=0;
	}
	
	@Override
	public int size() {
		return tama�o;
	}
	
	@Override
	public boolean isEmpty() {
		return tama�o==0;
	}
	
	@Override
	public E top() throws EmptyStackException {
		if (tama�o==0) {
			throw new EmptyStackException("Error, la pila est� vac�a.");
		}
		else {
			return head.getElemento();
		}
	}
	
	@Override
	public void push(E item) {
		Nodo <E> aux= new Nodo<E> (item,head);
		head=aux;
		tama�o++;
	}
	
	@Override
	public E pop() throws EmptyStackException {
		if (tama�o==0) {
			throw new EmptyStackException("Error, la pila est� vac�a.");
		}
		else {
			E aux=head.getElemento();
			head=head.getSiguiente();
			tama�o--;
			return aux;
		}
	}
}