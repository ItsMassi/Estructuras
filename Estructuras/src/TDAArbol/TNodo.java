package TDAArbol;
import TDALista.*;

/**
 * Define los datos y operaciones aplicables sobre un nodo de �rbol.
 * @author Juan Ignacio S�nchez.
 * @param <E> Tipo de dato de los elementos a almacenar en el nodo de �rbol.
 */
public class TNodo<E> implements Position<E> {
	
	/**
	 * R�tulo del nodo.
	 */
	private E elemento;
	
	/**
	 * Nodo padre del nodo actual.
	 */
	private TNodo<E> padre;
	
	/**
	 * Lista que contiene a todos los nodos hijos del nodo actual.
	 */
	private PositionList<TNodo<E>> hijos;
	
	/**
	 * Inicializa un nodo de �rbol con r�tulo, referencia al padre y sin hijos.
	 * @param elemento Elemento a establecer como elemento del nodo de �rbol.
	 * @param padre Nodo a establecer como el padre del nodo de �rbol.
	 */
	public TNodo (E elemento, TNodo<E> padre) {
		this.elemento=elemento;
		this.padre=padre;
		hijos=new ListaDoblementeEnlazada<TNodo<E>>();
	}
	
	@Override
	public E element() {
		return elemento;
	}
	
	/**
	 * Devuelve una lista que contiene todos los hijos del nodo de �rbol.
	 * @return Lista que contiene todos los hijos del nodo de �rbol.
	 */
	public PositionList<TNodo<E>> getHijos() {
		return hijos;
	}
	
	/**
	 * Devuelve el nodo padre del nodo de �rbol actual.
	 * @return Nodo padre del nodo de �rbol actual-
	 */
	public TNodo<E> getPadre() {
		return padre;
	}
	
	/**
	 * Establece el elemento del par�metro en el elemento del nodo de �rbol.
	 * @param elemento Elemento a establecer en el elemento del nodo de �rbol.
	 */
	public void setElemento(E elemento) {
		this.elemento=elemento;
	}
	
	/*public void setHijos(PositionList<TNodo<E>> h) {
		for (TNodo<E> t:h) {
			hijos.addLast(t);
		}
	}*/
	
	/**
	 * Establece el nodo del par�metro como el padre del nodo de �rbol actual.
	 * @param padre Nodo a establecer como el padre del nodo de �rbol actual.
	 */
	public void setPadre(TNodo<E> padre) {
		this.padre=padre;
	}
}