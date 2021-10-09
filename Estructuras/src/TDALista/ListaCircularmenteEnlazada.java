package TDALista;

public class ListaCircularmenteEnlazada<E> {
	protected Nodo<E> cursor;
	protected int size;
	
	public ListaCircularmenteEnlazada() {
		cursor=null;
		size=0;
	}
	
	public int size() {
		return size;
	}
	
	public Nodo<E> getCursor() {
		return cursor;
	}
	
	public void avanzarCursor() {
		cursor=cursor.getSiguiente();
	}
	
	public void agregar(Nodo<E> n) {
		if (cursor==null) {
			n.setSiguiente(n);//se sigue a si mismo pq es el unico nodo y es una lista circular
			cursor=n;
		}
		else {
			n.setSiguiente(cursor.getSiguiente());//n sigue al primer elemento
			cursor.setSiguiente(n);
		}
		size++;
	}
	
	public Nodo<E> remove() {
		Nodo<E> nodo=cursor.getSiguiente();
		if (nodo==cursor) {
			cursor=null;
		}
		else {
			cursor.setSiguiente(nodo.getSiguiente());
			nodo.setSiguiente(null);
		}
		size--;
		return nodo;
	}
	
	public String toString() {
		String s="";
		if (cursor==null) {
			s="[]";
		}
		else {
			Nodo<E> viejoCursor=cursor;
			s="[..."+cursor.element();
			for (avanzarCursor();viejoCursor!=cursor;avanzarCursor()) {
				s=s+", "+cursor.element();
			}
			s=s+"...]";
		}
		return s;
		}
}

