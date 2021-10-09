package TDAMapeo;
import TDAABB.*;
import java.util.Comparator;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;

public class MapeoConABB <K extends Comparable<K>,V> implements Map<K,V>{
	protected ArbolBinarioBusqueda<EntradaComparable<K,V>> abb;
	protected int size;
	
	public MapeoConABB() {
		abb= new ArbolBinarioBusqueda<EntradaComparable <K,V>>();
		size=0;
	}
	
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	@Override
	public V get(K key) throws InvalidKeyException {
		V retorna=null;
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}	
		EntradaComparable<K,V> e=new EntradaComparable<K,V>(key,null);
		NodoABB <EntradaComparable <K,V>> nodo=abb.buscar(e);
		if (nodo.getRotulo()!=null) {
			retorna=nodo.getRotulo().getValue();
		}
		return retorna;
	}
	
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		EntradaComparable <K,V> entrada;
		NodoABB <EntradaComparable <K,V>> nodo;
		V a_retornar = null;
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		entrada=new EntradaComparable <K,V> (key , value);
		nodo = abb.buscar(entrada);
		//Si el nodo correspondiente a entry es DUMMY, no existe en el ABB una entrada con clave key , por lo que se debe
		//agregar una nueva entrada. Caso contrario, existe entrada con clave key , y se debe modificar su valor
		if (nodo.getRotulo() == null ) {
			abb.expandir(nodo , entrada);
			size++;
		}
		else {
			a_retornar=nodo.getRotulo().getValue();
			nodo.getRotulo().setValue(value);
		}
		return a_retornar;
	}
	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		V retorna=null;
		EntradaComparable<K,V> e=new EntradaComparable<K,V>(key,null);
		NodoABB <EntradaComparable <K,V>> nodo=abb.buscar(e);
		if (nodo.getRotulo()!=null) {
			retorna=nodo.getRotulo().getValue();
			abb.eliminar(nodo.getRotulo());
			size--;
		}
		return retorna;
	}
	
	@Override
	public Iterable<K> keys() {
		PositionList<K> claves=new ListaDoblementeEnlazada<K>();
		keysAux(abb.getRaiz(),claves);
		return claves;
	}
	
	private void keysAux(NodoABB<EntradaComparable<K,V>> n, PositionList<K> l) {
		if (n.getRotulo()!=null) {
			l.addLast(n.getRotulo().getKey());
			keysAux(n.getLeft(),l);
			keysAux(n.getRight(),l);
		}
	}
	@Override
	public Iterable<V> values() {
		PositionList<V> valores=new ListaDoblementeEnlazada<V>();
		valuesAux(abb.getRaiz(),valores);
		return valores;
	}
	
	private void valuesAux(NodoABB<EntradaComparable<K,V>> n, PositionList<V> l) {
		if (n.getRotulo()!=null) {
			l.addLast(n.getRotulo().getValue());
			valuesAux(n.getLeft(),l);
			valuesAux(n.getRight(),l);
		}
	}
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> entradas=new ListaDoblementeEnlazada<Entry<K, V>>();
		entriesAux(abb.getRaiz(),entradas);
		return entradas;
	}
	
	private void entriesAux(NodoABB<EntradaComparable<K,V>> n, PositionList<Entry<K, V>> l) {
		if (n.getRotulo()!=null) {
			l.addLast(n.getRotulo());
			entriesAux(n.getLeft(),l);
			entriesAux(n.getRight(),l);
		}
	}
	

}
