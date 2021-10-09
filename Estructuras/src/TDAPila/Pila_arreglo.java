package TDAPila;

public class Pila_arreglo<E> implements Stack<E> {
	protected int tamaño;
	protected E[] datos;
	
	public Pila_arreglo(int max) {
		tamaño=0;
		datos= (E []) new Object[max];
	}
	public Pila_arreglo() {
		tamaño=0;
		datos=(E []) new Object[10];
	}
	public boolean isEmpty() {
		return tamaño==0;
	}
	public void push(E element) {
		if (tamaño==datos.length) {
			alargar();
		}
			datos[tamaño]=element;
			tamaño++;
	}
	public E pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Error, la pila está vacía");
		}
		else {
			E aux=datos[tamaño-1];//guardo el tope
			tamaño--;
			datos[tamaño]=null;//borro el tope
			return aux;
		}
	}
	public E top() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Error, la pila está vacía");
		}
		else {
			return datos[tamaño-1];
		}
	}
	public int size() {
		return tamaño;
	}
	public String toString() {
		String s="";
		for (int i=0;i<tamaño;i++) {
			s=s+" - "+datos[i];
		}
		return s;
	}
	
	public void invertir() {
		int tope=tamaño-1;
		E aux;
		for (int i=0;i<tamaño/2;i++) {
			aux=datos[i];
			datos[i]=datos[tope];
			datos[tope]=aux;
			tope--;
		}
	}
	private void alargar() {
		E [] aux= (E []) new Object[tamaño];
		aux=datos;
		datos=(E []) new Object[tamaño+10];
		for (int i=0;i<aux.length;i++) {
			datos[i]=aux[i];
		}
 	}
}
