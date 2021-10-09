package TDAArbol;
import TDALista.*;

/**
 * Define los datos y operaciones aplicables sobre un nodo de árbol.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos a almacenar en el nodo de árbol.
 */
public class TNodo<E> implements Position<E> {
	
	/**
	 * Rótulo del nodo.
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
	 * Inicializa un nodo de árbol con rótulo, referencia al padre y sin hijos.
	 * @param elemento Elemento a establecer como elemento del nodo de árbol.
	 * @param padre Nodo a establecer como el padre del nodo de árbol.
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
	 * Devuelve una lista que contiene todos los hijos del nodo de árbol.
	 * @return Lista que contiene todos los hijos del nodo de árbol.
	 */
	public PositionList<TNodo<E>> getHijos() {
		return hijos;
	}
	
	/**
	 * Devuelve el nodo padre del nodo de árbol actual.
	 * @return Nodo padre del nodo de árbol actual-
	 */
	public TNodo<E> getPadre() {
		return padre;
	}
	
	/**
	 * Establece el elemento del parámetro en el elemento del nodo de árbol.
	 * @param elemento Elemento a establecer en el elemento del nodo de árbol.
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
	 * Establece el nodo del parámetro como el padre del nodo de árbol actual.
	 * @param padre Nodo a establecer como el padre del nodo de árbol actual.
	 */
	public void setPadre(TNodo<E> padre) {
		this.padre=padre;
	}
}