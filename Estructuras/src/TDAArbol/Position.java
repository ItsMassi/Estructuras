package TDAArbol;

/**
 * Define las operaciones aplicables sobre una posición de árbol.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos que almacena la posición de árbol.
 */
public interface Position<E> {
	
	/**
	 * Devuelve el elemento de la posición de árbol. 
	 * @return Elemento de la posición de árbol.
	 */
	public E element();
}
