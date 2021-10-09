package TDADiccionario;


import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class DiccionarioConLista<K,V> implements Dictionary<K,V> {
	protected ListaDoblementeEnlazada<Entry<K,V>> D;
	
	public DiccionarioConLista() { 
		D = new ListaDoblementeEnlazada<Entry<K,V>>(); 
	}
	
	
	public Iterable<Entry<K,V>> entries() { return D; }


	@Override
	public int size() {
		return D.size();
	}


	@Override
	public boolean isEmpty() {
		return D.isEmpty();
	}


	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		// Si D contiene una entrada e con clave igual a key,
		// entonces retorna e, sino retorna null
		if(key==null) {throw new InvalidKeyException("si la clave pasada por parámetro es inválida");}

		Entry<K, V> retorno=null;
		
		for( Position<Entry<K,V>> p : D.positions() ) {
			if(p.element().getKey().equals(key)) {retorno=p.element();}
		}
		
		return retorno;
	}


	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key==null) {throw new InvalidKeyException("si la clave pasada por parámetro es inválida");}
		PositionList<Entry<K,V>> l =new ListaDoblementeEnlazada<Entry<K,V>>();
		for( Position<Entry<K,V>> p : D.positions() )
			if( p.element().getKey().equals(key) )
				l.addLast( p.element() );
				return l;
	}


	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		if(key==null) {throw new InvalidKeyException("si la clave pasada por parámetro es inválida");}
		else {
			Entry<K,V> e = (Entry<K, V>) new Entrada<K,V>(key, value);
			D.addLast( e ); // Asumo que addLast funciona en orden 1.
			return e;
		}	
	}


	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		Entry<K, V> retornar=null;
		if(e==null) {throw new InvalidEntryException( "Diccionario::remove: …" ); }
		try {
			for( Position<Entry<K,V>> p : D.positions()) {
				if( p.element() == e ) { D.remove(p);retornar=e;}
			}
			if(retornar==null) {throw new InvalidEntryException( "Diccionario::remove: …" );}
		}
		catch(InvalidPositionException f){
			System.out.println("Posicion Invalida");
		}
		return retornar;
	}
}