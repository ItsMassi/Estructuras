package TDAConjunto;

import java.util.Iterator;

public interface Conjunto<E> {
	
	// Inserta un item en el conjunto.
	public void agregar (E item);
	
	// Elimina el elemento especificado del conjunto. .
	public void eliminar (E item);
	
	// Retorna verdadero si un elemento pertenece al conjunto.
	public boolean pertenece (E item);
	
	// Realiza la union con otro conjunto c actualizando el conjunto receptor del mensaje.
	public void union (Conjunto<E> c);
	
	// Realiza la interseccion con otro conjunto c actualizando el conjunto receptor del mensaje.
	public void interseccion (Conjunto<E> c);
	
	public Iterator<E> iterator();
	
}
