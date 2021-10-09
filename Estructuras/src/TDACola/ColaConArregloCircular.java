package TDACola;

public class ColaConArregloCircular<E> implements Queue<E>{
	protected int frente;
	protected int rabo;
	protected E[] datos;
	
	public ColaConArregloCircular() {
		frente=0;
		rabo=0;
		datos=(E []) new Object[20];
	}
	
	public void enqueue(E element) {
		if (size()==datos.length-1) {
			alargarArreglo();
		}
		datos[rabo]=element;
		rabo=(rabo+1) % datos.length;
	}


	private void alargarArreglo() {
		try {
			int tama�o=size();
			E [] aux = (E []) new Object[tama�o];
			int i=0;
			while (!isEmpty()) {
				aux[i]=dequeue();
				i++;
			}
			datos = (E []) new Object[2*tama�o]; 
			i=0;
			while (i<aux.length) {
				enqueue(aux[i]);
				i++;
			}
		}
		catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	
	public E dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Error, la cola est� vacia");
		}
		else {
			E aux=datos[frente];
			datos[frente]=null;
			frente=(frente+1) % datos.length;
			return aux;
		}
	}
	
	public boolean isEmpty() {
		return frente==rabo;
	}
	
	public E front() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Error, la cola est� vac�a");
		}
		else {
			return datos[frente];
		}
	}
	
	public int size() {
		return (datos.length-frente+rabo) % datos.length;
	}
}


