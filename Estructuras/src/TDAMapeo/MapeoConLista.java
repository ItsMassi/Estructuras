package TDAMapeo;
import TDALista.*;

/**
 * Define los datos y operaciones aplicables sobre un mapeo con lista.
 * @author Juan Ignacio Sánchez.
 * @param <K> Tipo de dato a almacenar en la clave de las entradas del mapeo con lista.
 * @param <V> Tipo de dato a almacenar en el valor de las entradas del mapeo con lista.
 */
public class MapeoConLista<K,V> implements Map<K,V> {
	
	/**
	 * Lista de entradas que modela la tabla hash.
	 */
	protected ListaDoblementeEnlazada<Entrada<K,V>> s;
	
	/**
	 * Inicializa un mapeo con lista vacío.
	 */
	public MapeoConLista() {
		s= new ListaDoblementeEnlazada<Entrada<K,V>>();
	}
	
	@Override
	public int size() {
		return s.size();
	}
	
	@Override
	public boolean isEmpty() {
		return s.isEmpty();
	}
	
	@Override
	public V get(K key) throws InvalidKeyException {
		V value=null;
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		for (Position<Entrada<K,V>> p:s.positions()) {
			if (p.element().getKey().equals(key)) {
				value=p.element().getValue();
			}
		}
		return value;
	}
	
	@Override
	public V put(K key,V value) throws InvalidKeyException {
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		for (Position<Entrada<K,V>> p: s.positions()) {
			if (p.element().getKey().equals(key)) {//si ya había una entrada con la misma clave, retorna el valor de esa entarda
				V aux=p.element().getValue();
				p.element().setValue(value);//modifica el de la entrada con el del parámetro
				return aux;
			}
		}
		s.addLast(new Entrada<K,V>(key,value));//si no había una entrada con la misma clave, crea la entrada y la inserta
		return null;
	}
	@Override
	public V remove(K key) throws InvalidKeyException {
		V value=null;
		try {
			if (key==null) {
				throw new InvalidKeyException("clave invalida");
			}
			for (Position<Entrada<K,V>> p: s.positions()) {
				if (p.element().getKey().equals(key)) {
					value=p.element().getValue();
					s.remove(p);
				}
			}
		}
		catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	@Override
	public Iterable<K> keys() {
		PositionList<K> l = new ListaDoblementeEnlazada<K>();
		Position<Entrada<K,V>> n;
		K p = null;
		try{
			if(!s.isEmpty()) {//si s no esta vacía, guarda todas las claves en l
				n=s.first();
				p = n.element().getKey();
				while(n != s.last()) {
					l.addLast(p);
					n=s.next(n);
					p = n.element().getKey();
				}
				l.addLast(p);
			}
		}
		catch(BoundaryViolationException e) {
			e.printStackTrace();
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(EmptyListException e){
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public Iterable<V> values() {
		PositionList<V> l = new ListaDoblementeEnlazada<V>();
		Position<Entrada<K,V>> n;
		V p = null;
		try{
			if(!s.isEmpty()) {//si s no esta vacía, guarda todos los valores en l
				n=s.first();
				p = n.element().getValue();
				while(n != s.last()) {
					l.addLast(p);
					n=s.next(n);
					p = n.element().getValue();
				}
				l.addLast(p);
			}
		}
		catch(BoundaryViolationException e) {
			e.printStackTrace();
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(EmptyListException e){
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public Iterable<Entry<K,V>> entries() {
		PositionList<Entry<K,V>> l = new ListaDoblementeEnlazada<Entry<K,V>>();
		Position<Entrada<K,V>> n;
		Entry<K,V> p = null;
		try{
			if(!s.isEmpty()) {//si s no esta vacía, guarda todos las entradas en l
				n=s.first();
				p = n.element();
				while(n != s.last()) {
					l.addLast(p);
					n=s.next(n);
					p = n.element();
				}
				l.addLast(p);//guardo el ultimo elemento de s
			}
		}
		catch(BoundaryViolationException e) {
			e.printStackTrace();
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(EmptyListException e){
			e.printStackTrace();
		}
		return l;
	}
}
