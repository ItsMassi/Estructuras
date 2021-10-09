package TDAPila;

public class Pila_arreglo<E> implements Stack<E> {
	protected int tama�o;
	protected E[] datos;
	
	public Pila_arreglo(int max) {
		tama�o=0;
		datos= (E []) new Object[max];
	}
	public Pila_arreglo() {
		tama�o=0;
		datos=(E []) new Object[10];
	}
	public boolean isEmpty() {
		return tama�o==0;
	}
	public void push(E element) {
		if (tama�o==datos.length) {
			alargar();
		}
			datos[tama�o]=element;
			tama�o++;
	}
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Error, la pila est� vac�a");
		}
		else {
			E aux=datos[tama�o-1];//guardo el tope
			tama�o--;
			datos[tama�o]=null;//borro el tope
			return aux;
		}
	}
	public E top() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Error, la pila est� vac�a");
		}
		else {
			return datos[tama�o-1];
		}
	}
	public int size() {
		return tama�o;
	}
	public String toString() {
		String s="";
		for (int i=0;i<tama�o;i++) {
			s=s+" - "+datos[i];
		}
		return s;
	}
	
	public void invertir() {
		int tope=tama�o-1;
		E aux;
		for (int i=0;i<tama�o/2;i++) {
			aux=datos[i];
			datos[i]=datos[tope];
			datos[tope]=aux;
			tope--;
		}
	}
	private void alargar() {
		E [] aux= (E []) new Object[tama�o];
		aux=datos;
		datos=(E []) new Object[tama�o+10];
		for (int i=0;i<aux.length;i++) {
			datos[i]=aux[i];
		}
 	}
}
