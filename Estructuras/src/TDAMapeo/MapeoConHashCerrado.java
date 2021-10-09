package TDAMapeo;


import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class MapeoConHashCerrado<K, V>  implements Map<K,V> {

	protected Entrada<K,V> [] A;
	protected int n;
	protected int N=13;
	protected final Entrada<K,V> bucket_disponible = new Entrada<K,V>(null,null);
	protected final float FactorDeCarga=0.9f;
	
	
	public MapeoConHashCerrado() {
			n=0;
			A = (Entrada<K,V>[]) new Entrada[N];
			for(int i=0; i<N; i++){
			A[i] = null;
		}
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return n==0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if(key==null) {throw new InvalidKeyException("La clave pasada por parámetro es inválida");}
		V retornar=null;boolean parar=false;
		int pos=funcion_hash(key);
		
		while (A[pos]!=null && !parar) {
			if (A[pos] != bucket_disponible && A[pos].getKey().equals(key)){
				parar=true;
				retornar=A[pos].getValue();
			}
			pos= (pos+1) % N;
		}
		return retornar;
	}
	
		
	

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		Entrada<K, V>entrada=null;
		int bucket=0;
		V retornar=null;
		if(key==null) {
			throw new InvalidKeyException("Clave invalida");
		}
		else {
			if(n/N>=FactorDeCarga) {redimensionar();}
			entrada=new Entrada<K,V>(key, value);
			bucket=funcion_hash(key);
			retornar=insert_segun_politica(entrada,bucket);
			n++;
		}
		return retornar;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		int pos=0;
		V retornar=null;
		int cantidad=0;
		boolean parar=false;
		if (key==null) {
			throw new InvalidKeyException("Entrada inválida");
		}
		
		pos=funcion_hash(key);
		while (A[pos]!=null && !parar&&cantidad<N) {
			if (A[pos]!=bucket_disponible && A[pos].getKey().equals(key)) {
				retornar=A[pos].getValue();
				A[pos]=bucket_disponible;
				parar=true;n--;
			}
			else {pos= (pos+1) % N;cantidad++;}
			
		}

		return retornar;
	}

	@Override
	public Iterable<K> keys() {
		int pos=0; 
		PositionList<K> l =new ListaDoblementeEnlazada<K>();		
		while(pos<N) {
			if(A[pos]!=null&&A[pos]!=bucket_disponible) {
				l.addLast( A[pos].getKey() );
				pos++;			
			}
			else {pos++;}
		}
		return l;
	}

	@Override
	public Iterable<V> values() {
		int pos=0; 
		PositionList<V> l =new ListaDoblementeEnlazada<V>();		
		while(pos<N) {
			if(A[pos]!=null&&A[pos]!=bucket_disponible) {
				l.addLast( A[pos].getValue() );
				pos++;			
			}
			else {pos++;}
		}
		return l;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		int pos=0; 
		PositionList<Entry<K,V>> l =new ListaDoblementeEnlazada<Entry<K,V>>();		
		while(pos<N) {
			if(A[pos]!=null&&A[pos]!=bucket_disponible) {
				l.addLast( A[pos] );
				pos++;			
			}
			else {pos++;}
		}
		return l;
	}
	public int funcion_hash(K key) {

		return Math.abs(key.hashCode()) % N;
	}
	public void redimensionar() {
		
		int bucket=0;
		N=proximo_primo(2*N);
		Entrada<K,V> entrada=null;
		Entrada<K,V> [] nuevo=A;
		A = new Entrada [N];
		for (int i=0;i<nuevo.length;i++) {
			entrada=nuevo[i];
			if (entrada!=null && entrada!=bucket_disponible) {
				bucket=funcion_hash(entrada.getKey());
				insert_segun_politica(entrada,bucket);
			}
		}
	}
	/**
	 * Devuelve un entero que es el próximo primo al pasado por parámetro
	 * @param n numero entero, que se forma de multilicar por dos un nro primo
	 * @return retorna un entero que es el próximo primo al pasado por parámetro
	 */
	private static int proximo_primo(int n) {
		
		boolean parar=false;
		int aux=n+1;
		while(!parar) {			
			for(int i=2;i<aux&&!parar;i++) {
				if((aux % i==0)) {parar=true;}
			}
			if(parar) {aux++;parar=false;}
			else {parar=true;}

		}
		return aux;
	}
	private V insert_segun_politica(Entrada<K,V> e, int bucket) {
		V retornar=null;
		boolean esta=false;
		boolean parar=false;
		int pos=bucket;
		int cantidad=0;
		try {
		if(get(e.getKey())!=null) {esta=true;}
		while (!parar&&cantidad<N) {
			
			if(esta) {
				if(A[pos].getKey()==e.getKey()) {
					retornar=A[pos].getValue();
					parar=true;
					A[pos].setValue(e.getValue());}
				else {
					
					pos=(pos+1)%N;cantidad++;
				}
			}
			else {
				if (A[pos]==null || A[pos]==bucket_disponible) {
				A[pos]=e;
	
				parar=true;
				}
				else{
					pos=(pos+1)%N;cantidad++;
					
				}
			}
			
		}
		}
		catch(InvalidKeyException r) {
			System.out.println("Incvalido");
		}
		
		return retornar;
		
	}
	
}