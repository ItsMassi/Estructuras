package TDADiccionario;
import TDALista.PositionList;
import TDALista.EmptyListException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDAABB.*;

public class DiccionarioConABB <K extends Comparable<K>,V> implements Dictionary<K,V>{
	protected ArbolBinarioBusqueda<EntradaComparable <K,PositionList<Entry <K,V>>>> abb;
	protected int size;
	
	public DiccionarioConABB() {
		size=0;
		abb=new ArbolBinarioBusqueda<EntradaComparable <K,PositionList<Entry <K,V>>>>();
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		Entry<K,V> retorna=null;
		V valor=null;
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		try {
			EntradaComparable<K,PositionList<Entry <K,V>>> e=new EntradaComparable<K,PositionList<Entry<K,V>>>(key,null);
			NodoABB<EntradaComparable <K,PositionList<Entry<K,V>>>> nodo=abb.buscar(e);
			if (nodo.getRotulo()!=null && !nodo.getRotulo().getValue().isEmpty()) {
				valor=nodo.getRotulo().getValue().first().element().getValue();
				retorna=new EntradaComparable<K,V>(key,valor);
			}
		}
		catch(EmptyListException ex) {
			ex.printStackTrace();
		}
		return retorna;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		PositionList<Entry<K, V>> retorna=new ListaDoblementeEnlazada<Entry<K, V>>();
		EntradaComparable<K,PositionList <Entry <K,V>>> e=new EntradaComparable< K,PositionList <Entry<K,V>>>(key , null);
		if (!isEmpty()) {
			NodoABB<EntradaComparable <K,PositionList <Entry <K,V>>>> nodo=abb.buscar(e);
			retorna=nodo.getRotulo().getValue();
		}
		return retorna;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		EntradaComparable<K,PositionList <Entry <K,V>>> entrada;
		NodoABB<EntradaComparable <K,PositionList <Entry <K,V>>>> nodo;
		Entry<K,V> a_retornar;
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		entrada=new EntradaComparable<K,PositionList<Entry<K,V>>>(key,null);
		nodo=abb.buscar(entrada);
		a_retornar= new EntradaComparable <K,V> (key , value);
		//Si el nodo correspondiente a entry no es DUMMY, existe al menos una entrada en ABB con la clave key
		if (nodo.getRotulo()!= null) {
			nodo.getRotulo().getValue().addLast(a_retornar);
		}
		else {
			abb.expandir(nodo , entrada);
			entrada.setValue(new ListaDoblementeEnlazada <Entry<K,V>>());
			entrada.getValue().addLast(a_retornar);
		}
		size++;
		return a_retornar;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e==null) {
			throw new InvalidEntryException("Clave inválida");
		}
		Entry<K,V> retorna=null;
		EntradaComparable<K,PositionList <Entry <K,V>>> en=new EntradaComparable< K,PositionList <Entry<K,V>>>(e.getKey() , null);
		NodoABB <EntradaComparable<K,PositionList<Entry<K,V>>>> nodo=abb.buscar(en);
		try {
			if (nodo.getRotulo()!=null) {
				size--;
				retorna=new EntradaComparable<K,V>(e.getKey(),e.getValue());
				for (Position<Entry<K,V>> entrada:nodo.getRotulo().getValue().positions()) {//busco la entrada en la lista
					if (entrada.element().getKey().equals(e.getKey()) && entrada.element().getValue().equals(e.getValue())) {
						nodo.getRotulo().getValue().remove(entrada);//remueve la entrada de la lista
						break;
					}
				}
				if (nodo.getRotulo().getValue().isEmpty()) {//si en la lista de la entrada esta vacia, (no hay ninguna entrada con key)
					abb.eliminar(nodo.getRotulo());//la elimino del arbol
				}
			}
			else {
				throw new InvalidEntryException("La entrada no pertenece al diccionario");
			}
		}
		catch(TDALista.InvalidPositionException ex) {
			ex.printStackTrace();
		}
		return retorna;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> lista=new ListaDoblementeEnlazada<Entry<K,V>>();
		if (!isEmpty()) {
			entriesAux(abb.getRaiz(),lista);
		}
		return lista;
	}
	
	private void entriesAux(NodoABB<EntradaComparable <K,PositionList <Entry <K,V>>>> e, PositionList<Entry<K,V>> l) {
		if (e.getRotulo()!=null) {
			for (Entry<K,V> entradas:e.getRotulo().getValue()) {//cada entrada de una determinada clave
				l.addLast(entradas);
			}
			entriesAux(e.getLeft(),l);
			entriesAux(e.getRight(),l);
		}
	}
}
