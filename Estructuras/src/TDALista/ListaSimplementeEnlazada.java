package TDALista;

import java.util.Iterator;
import TDALista.*;

import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class ListaSimplementeEnlazada<E> implements PositionList<E> {
	protected Nodo<E> cabeza;
	protected int tamaño;
	
	public ListaSimplementeEnlazada( ) {
		cabeza=null;
		tamaño=0;
	}
	
	private ListaSimplementeEnlazada(Position<E> p) {
		cabeza=(Nodo<E>) p;
		tamaño=0;
	}
	
	public int size() {
		return tamaño;
	}
	
	public boolean isEmpty() {
		return cabeza==null;
	}
	
	public void addFirst (E e) {
		cabeza=new Nodo<E>(e,cabeza);//agrega una cabeza antes de la cabeza que estaba antes
		tamaño++;
	}
	
	public void addLast(E e) {
		if (isEmpty()) {
			addFirst(e);
		}
		else {
			Nodo<E> p=cabeza;
			while (p.getSiguiente()!=null) {
				p=p.getSiguiente();//busco el ultimo nodo
			}
			p.setSiguiente(new Nodo<E>(e));//pongo el elemento despues del ultimo nodo
			tamaño++;
		}
	}
	
	public Position<E> first () throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("Error, la lista está vacía");
		}
		else {
			return cabeza;
		}
	}

	public Position<E> last()  throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("Error, la lista está vacía");
		}
		else {
			Nodo<E> ultimo=cabeza;
			while(ultimo.getSiguiente()!=null) {
				ultimo=ultimo.getSiguiente();
			}
			return ultimo;
		}
	}
	
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		try {
			checkPosition(p);
			if(p == first()){
				throw new BoundaryViolationException("Primera Posicion");
			}
			Nodo<E> aux = cabeza;
			while(aux.getSiguiente() != p && aux.getSiguiente() != null){
				aux = aux.getSiguiente();
			}
			if(aux.getSiguiente() == null){
				throw new InvalidPositionException("La posicion no es de esta lista");
			}
			return aux;
		}catch(EmptyListException e){
			throw new  InvalidPositionException("Pila Vacia");
		}
	}
	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n= checkPosition(p);
		if (n.getSiguiente()==null) {
			throw new BoundaryViolationException("Error, siguiente de ultimo");
		}
		else {
			return n.getSiguiente();
		}
	}
	
	private Nodo<E> checkPosition(Position<E> n) throws InvalidPositionException {
		try {
			if (n==null) {
				throw new InvalidPositionException("Error, posición nula");
			}
			if (n.element()==null) {
				throw new InvalidPositionException("El elemento fue eliminado previamente");
			}
			return (Nodo<E>) n;
		}
		catch (InvalidPositionException e) {
			throw new InvalidPositionException("n no es un nodo de esta lista");
		}
	}
	
	public void addAfter(Position<E> p,E e) throws InvalidPositionException {
		Nodo<E> n= (Nodo<E>) checkPosition(p);
		Nodo<E> nuevo=new Nodo<E>(e);
		nuevo.setSiguiente(n.getSiguiente());//al nuevo le pongo como siguiente al que tenia n
		n.setSiguiente(nuevo);//a n le pongo como siguiente a nuevo
	}
	
	public void addBefore(Position<E> p,E e) throws InvalidPositionException {
		try {
			Nodo<E> n= (Nodo<E>) checkPosition(p);
			if (p==first()) {
				addFirst(e);
			}
			else {
				addAfter(prev(p),e);
			}
		}
		catch (EmptyListException ex) {
			ex.printStackTrace();
		}
		catch (BoundaryViolationException ex1) {
			ex1.printStackTrace();
		}
	}
	
	public E remove(Position<E> p) throws InvalidPositionException {
		try {
			Nodo<E> n=checkPosition(p);
			if (p==first()) {
				cabeza=n.getSiguiente();
				tamaño--;
			}
			else {
				checkPosition(prev(p)).setSiguiente(n.getSiguiente());
				tamaño--;
			}
			E aux=p.element();
			n.setElemento(null);
			n.setSiguiente(null);
			return aux;
		}
		catch (EmptyListException ex) {
			ex.printStackTrace();
			return null;	
		}
		catch (BoundaryViolationException ex) {
			ex.printStackTrace();
			return null;	
		}
	}
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n=checkPosition(p);
		E aux=n.element();
		n.setElemento(element);
		return aux;
	}
	public Iterator<E> iterator(){
		return new ElementIterador<E>(this);
	}
	
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> l = new ListaSimplementeEnlazada<Position<E>>();
		Position<E> p = null;
		try{
			if(!isEmpty()){
				p = first();
			}else{
				return l;
			}
			while(p != last()){
				l.addLast(p);
				p = next(p);
			}
			l.addLast(p);
		}catch(BoundaryViolationException e){
			System.out.println("Se pasa");
		}catch(InvalidPositionException e){
			System.out.println("Posicion Invalida");
		}catch(EmptyListException e){
			System.out.println("Lista Vacia");
		}
		return l;
	}
	
	/////////////////////////////////////////////////////////OPERACIONES/////////////////////////////////////////////////////////////////////////
	public void eliminarConsecutivos(E e1,E e2) {
		try {
			Nodo<E> nodo=(Nodo<E>) first();
			for (int i=0;i<size()-1;i++) {
				if (nodo.element()==e1 && nodo.getSiguiente().element()==e2) {
					Nodo<E> borrar=nodo.getSiguiente();
					remove(nodo);
					remove(borrar);
				}
				nodo=nodo.getSiguiente();
			}
		}
		catch(EmptyListException e){
			System.out.println("Lista Vacia");
		}
		catch(InvalidPositionException e){
			System.out.println("Posición inválida");
		}
	}

	public ListaSimplementeEnlazada<E> clone() {
		Nodo<E> primero=(Nodo<E>) cabeza;
		Nodo<E> sig=cabeza.getSiguiente();
		primero=primero.clone();
		ListaSimplementeEnlazada<E> l=new ListaSimplementeEnlazada(primero);
		try {
			l.addFirst(cabeza.element());
			for (int i=0;i<size()-1;i++) {
				l.addAfter((Position<E>)primero,sig.element());
				sig=sig.getSiguiente();
				
			}
		}
		catch(InvalidPositionException e){
			System.out.println("Lista Vacia");
		}
		return l;
	}
}
