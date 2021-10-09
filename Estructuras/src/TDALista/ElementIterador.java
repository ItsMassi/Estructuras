package TDALista;

import java.util.*;
import java.lang.*;

public class ElementIterador<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> cursor;
	
	public ElementIterador (PositionList<E> l) {
		try {
			list=l;
			if (list.isEmpty()) {
				cursor=null;			
			}
			else {
				cursor=list.first();
			}
		}
		catch (EmptyListException e) {
			
		}
	}
	
	public boolean hasNext() {
		return cursor!=null;
	}
	
	public E next () throws NoSuchElementException {
		try {
			if (cursor==null) {
				throw new NoSuchElementException("Error, no hay siguiente");
			}
			E toReturn=cursor.element();
			cursor=(cursor==list.last()) ? null:list.next(cursor);
			return toReturn;
		}
		catch (EmptyListException e) {
			throw new  NoSuchElementException("Pila Vacia");
		}
		catch (InvalidPositionException e) {
			throw new  NoSuchElementException("Pila Vacia");
		}
		catch (BoundaryViolationException e) {
			throw new  NoSuchElementException("Pila Vacia");
		}
	}
	
	
}
