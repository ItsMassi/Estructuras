package TDADiccionario;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;




public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V> {
	PositionList<Entry<K,V>>[] A;
	protected int n;
	
	protected int N=13;
	
	protected final float FactorDeCarga=0.5f;

	public DiccionarioConHashAbierto() {
		n=0;
		A=((PositionList<Entry<K,V>>[]) new ListaDoblementeEnlazada[N]);
		for(int i=0;i<N;i++) {
			A[i]= new ListaDoblementeEnlazada<Entry<K,V>>();
		}	
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		int pos=0;
		
		Entry<K,V> entrada=null;
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		else {
		pos=funcion_hash(key);
			if(A[pos]!=null) {		
					for(Position<Entry<K, V>> d: A[pos].positions() ) {
						if (d.element().getKey().equals(key)){
							entrada= d.element();
						}	
					}
			}

		}
		
		return entrada;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		int pos=0;
		PositionList<Entry<K, V>> aux=new ListaDoblementeEnlazada<Entry<K, V>>();
		if (key==null) {
			throw new InvalidKeyException("Clave inválida");
		}
		else {
			pos=funcion_hash(key);
			if(A[pos]!=null) {		
				for(Position<Entry<K, V>> d: A[pos].positions() ) {
					if (d.element().getKey().equals(key)){
							aux.addLast(d.element());
					}
				}
						
			}
		}
		
		return aux;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		 Entry<K, V> retornar=null;
		 int bucket=0;
			if(key==null) {
				throw new InvalidKeyException("Clave invalida");
			}
			else {
				if(n/N>=FactorDeCarga) {redimensionar();}
				retornar=new Entrada<K,V>(key, value);
				bucket=funcion_hash(key);
				A[bucket].addLast(retornar);
				n++;
			}
		 return retornar;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		Entry<K,V> entrada=null;
		int pos=0;
		boolean parar=false;
		if (e==null) {
			throw new InvalidEntryException("Entrada inválida");
		}
		try {
			pos=funcion_hash(e.getKey());
			if(A[pos]!=null) {
				for(Position<Entry<K,V>> w:A[pos].positions()) {
					if (w.element()==e&&!parar) {
						entrada= w.element();
						A[pos].remove(w);
						parar=true;
					}
					
				}
			}
		
			if (entrada==null) {
				throw new InvalidEntryException("La entrada no pertenece al diccionario");
			}
			n--;
		}
		catch(InvalidPositionException a) {
			System.out.println("Posición inválida");
		}
		return entrada;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> l = new ListaDoblementeEnlazada<Entry<K, V>>();
		ListaDoblementeEnlazada<Entry<K,V>> map=null;
		 
			for(int i=0;i<N;i++) {
				if(!(A[i].isEmpty())) {
					map= ((ListaDoblementeEnlazada<Entry<K,V>>) A[i]);
					for(Position<Entry<K, V>> w: map.positions()) {
						l.addLast(w.element());
					}
					
				}
			}
			
		return l;
	}
	protected int funcion_hash(K clave){
		return Math.abs(clave.hashCode() % A.length);
	}
	public void redimensionar() {
		
		int bucket=0;
		N=proximo_primo(2*N);
		PositionList<Entry<K,V>>[] nuevo=A;
		A = ((PositionList<Entry<K,V>>[]) new ListaDoblementeEnlazada[N]);
		for(int i=0;i<N;i++) {
			A[i]= new ListaDoblementeEnlazada<Entry<K,V>>();
		}	
		for (int i=0;i<nuevo.length;i++) {
			for(Position<Entry<K,V>> w: nuevo[i].positions()) {
					bucket=funcion_hash(w.element().getKey());
					A[bucket].addLast(new Entrada<K,V>(w.element().getKey(),w.element().getValue()));	
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
	
	////////////////////////////////////////////////////////OPERACIONES////////////////////////////////////////////////////////////////////////////
	public void convertirAMapeo() {
		try {
			if (n!=0) {//si el diccionario no esta vacío
				for(int i=0;i<A.length;i++) {//recorro todo el arreglo
					if (!A[i].isEmpty()) {//si el elemento deol arreglo no esta vacio
						Position<Entry<K,V>> ult=A[i].last();//lo recorro del final al principio para que me quede la primer entrada repetida
						while (ult!=A[i].first()) {
							Position<Entry<K,V>> aux=A[i].prev(ult);//guardo el previo en un aux pq despues elimino a ult
							if(clavesIguales(ult.element().getKey())) {
								remove(ult.element());
							}
							ult=aux;
						}
					}
				}
			}
		}
		catch(InvalidEntryException e) {
			e.printStackTrace();
		}
		catch(EmptyListException e) {
			e.printStackTrace();
		}
		catch(TDALista.BoundaryViolationException e) {
			e.printStackTrace();
		}
		catch(TDALista.InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	private boolean clavesIguales(K key) {
		int cont=0;
		boolean hayIguales=false;
		for (PositionList<Entry<K,V>> l:A) {
			for (Entry<K,V> e:l) {
				if (e.getKey().equals(key)) {
					cont++;
					System.out.println(e.getValue()+" "+cont);
					if (cont==2) {
						hayIguales=true;
						break;
					}
				}
			}
			if (hayIguales) {
				break;
			}
		}
		return hayIguales;
	}
	
}
