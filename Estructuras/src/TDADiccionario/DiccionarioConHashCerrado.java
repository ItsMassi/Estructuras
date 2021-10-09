package TDADiccionario;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

/**
 * Define los datos y operaciones aplicables sobre un diccionario con hash cerrado.
 * @author Juan Ignacio Sánchez.
 * @param <K> Tipo de dato a almacenar en la clave de las entradas del diccionario con hash cerrado.
 * @param <V> Tipo de dato a almacenar en el valor de las entradas del diccionario con hash cerrado.
 */
public class DiccionarioConHashCerrado<K,V> implements Dictionary<K,V> {
	
	/**
	 * Arreglo de entradas que modela la tabla hash.
	 */
	protected Entrada<K,V> [] arreglo;
	
	/**
	 * Cantidad de entradas del diccionario con hash cerrado.
	 */
	protected int n;
	
	/**
	 * Tamaño del arreglo.
	 */
	protected int N=1;
	
	/**
	 * Entrada vacía que modela un bucket disponible.
	 */
	protected final Entrada<K,V> bucketDisponible=new Entrada<K,V>(null,null);
	
	/**
	 * Factor de carga de la tabla hash.
	 */
	protected final float factorDeCarga=0.5f;
	
	/**
	 * Inicializa un diccionario con hash cerrado vacío.
	 */
	public DiccionarioConHashCerrado() {
		n=0;
		arreglo= new Entrada[N];
	}
	
	/**
	 * Devuelve un número con el código hash de la clave key.
	 * @param key Clave a partir de la cual se calcula el código hash.
	 * @return Número con el código hash de la clave key.
	 */
	private int funcionHash(K key) {
		return Math.abs(key.hashCode() % N);
	}
	
	@Override
	public int size() {
		return n;
	}
	
	@Override
	public boolean isEmpty() {
		return n==0;
	}
	
	@Override
	public Entry<K,V> find(K key) throws InvalidKeyException {
		int bucket=0;
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		bucket=funcionHash(key);
		return  buscarClave(key,bucket);
	}
	
	/**
	 * Busca, a partir de un bucket p, una entrada con clave igual a key y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @param p Entero con el bucket a partir del cual comienza la búsqueda.
	 * @return Entrada encontrada.
	 */
	private Entry<K,V> buscarClave(K key, int p) {
		Entrada<K,V> retorna=null;
		int bucket=p;
		boolean encontro=false;
		for (int i=0;i<arreglo.length && !encontro && arreglo[bucket]!=null;i++) {//como mucho recorre una sola vez el arreglo circularmente
			if (arreglo[bucket]!=bucketDisponible && arreglo[bucket].getKey().equals(key)){
				encontro=true;
				retorna=arreglo[bucket];
			}
			bucket= (bucket+1) % N;//avanza de forma circular
		}
		return retorna;
	}
	
	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		Entrada<K,V> entrada;
		int bucket;
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		if (n/N>=factorDeCarga) {
			rehash();
		}
		entrada=new Entrada<K,V>(key, value);
		bucket=funcionHash(key);
		insertarSegunPolitica(entrada,bucket);
		n++;
		return entrada;
	}
	
	/**
	 * Busca,a partir de un bucket p, un próximo bucket nulo o vacío donde inserta la entrada e.
	 * @param e Entrada a insertar en el próximo bucket nulo o disponible.
	 * @param p Entero que contiene el bucket a partir del cual se inicia la búsqueda del próximo bucket nulo o disponible.
	 */
	private void insertarSegunPolitica(Entrada<K,V> e, int p) {
		boolean inserto=false;
		int bucket=p;
		while (!inserto) {
			if (arreglo[bucket]==null || arreglo[bucket]==bucketDisponible) {
				arreglo[bucket]=e;
				inserto=true;
			}
			bucket=(bucket+1)%N;//avanza de forma circular
		}
	}
	
	/**
	 * Aumenta el tamaño del arreglo de entradas con el siguiente numero primo del doble de su anterior tamaño.
	 */
	private void rehash() {
		int bucket;
		N=siguientePrimo(2*N);
		Entrada<K,V> [] aux=arreglo;
		arreglo = new Entrada [N];
		for (Entrada<K,V> entrada:aux) {
			if (entrada!=null && entrada!=bucketDisponible) {
				bucket=funcionHash(entrada.getKey());
				insertarSegunPolitica(entrada,bucket);//inserto las entradas con el código hash actualizado
			}
		}
	}
	
	/**
	 * Devuelve el siguiente numero primo al entero p.
	 * @param p Entero a partir del cual se busca su siguiente número primo.
	 * @return Siguiente numero primo al entero p.
	 */
	private int siguientePrimo(int p) {
		boolean encontro=false;
		int retorna=0;
		if (esPrimo(p)) {//para el caso cuando N=1, donde p sería igual a 2
			encontro=true;
			retorna=p;
		}
		else {
			p++;
			for (int i=p;!encontro;i++) {
				if (esPrimo(i)) {
					encontro=true;
					retorna=i;
				}
			}
		}
		return retorna;
	}
	
	/**
	 * Consulta si el entero p es un número primo.
	 * @param n Entero a determinar si es un número primo.
	 * @return Verdadero si el entero p es un número primo, falso en caso contrario.
	 */
	private boolean esPrimo(int n){
		int cont=0;
		  boolean primo=true;
		  for (int i=1;i<n && primo;i++) {
			  if (n%i==0) {
				  cont++;
			  }
			  primo=cont<2;
		  }
		  return primo;
	}	
	
	@Override
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException {
		Entrada<K,V> retorna=null;
		int bucket;
		if (e==null) {
			throw new InvalidEntryException("Entrada inválida");
		}
		bucket=funcionHash(e.getKey());
		retorna=buscarEntrada(e, bucket);
		if (retorna==null) {
			throw new InvalidEntryException("La entrada no pertenece al diccionario");
		}
		n--;
		return retorna;
	}
	
	/**
	 * Busca, a partir de un bucket p, una entrada igual a la entrada e y la devuelve, si no existe retorna nulo.
	 * @param e Entrada a buscar.
	 * @param p Entero con el bucket a partir del cual comienza la búsqueda.
	 * @return Entrada encontrada.
	 */
	private Entrada<K,V> buscarEntrada(Entry<K,V> e, int p) {
		boolean encontro=false;
		Entrada<K,V> retorna=null;
		int bucket=p;
		for (int i=0;i<arreglo.length && !encontro && arreglo[bucket]!=null;i++) {//como mucho recorre una sola vez el arreglo circularmente
			if (arreglo[bucket]!=bucketDisponible && arreglo[bucket].getKey().equals(e.getKey()) && arreglo[bucket].getValue().equals(e.getValue())) {
				retorna=arreglo[bucket];
				arreglo[bucket]=bucketDisponible;//elimina el bucket, dejándolo disponible 
				encontro=true;
			}
			bucket= (bucket+1) % N;//avanza de forma circular
		}
		return retorna;	
	}
	
	@Override
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException {
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		PositionList<Entry<K,V>> l = new ListaDoblementeEnlazada<Entry<K,V>>();
		for (Entry<K,V> e:arreglo) {
			if (e!=bucketDisponible && e!=null && e.getKey().equals(key)) {
				l.addLast(e);
			}
		}
		return l;
	}
	
	@Override
	public Iterable<Entry<K,V>> entries() {
		PositionList<Entry<K,V>> l = new ListaDoblementeEnlazada<Entry<K,V>>();
		for (Entry<K,V> e:arreglo) {
			if (e!=null && e!=bucketDisponible)
				l.addLast(e);
		}
		return l;
	}
}
