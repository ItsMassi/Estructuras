package TDAMapeo;

public class MapeoConTrie<E> implements Map<String, E>{
	protected NodoTrie<E> raiz;
	protected int size;
	
	public MapeoConTrie() {
		raiz = new NodoTrie<E>(null);
		size=0;
	}
	
	
	public E put( String clave, E valor ) {
		// Recorro clave desde índice 0 y el árbol desde la raíz
		return putaux( clave, valor, 0, clave.length(), raiz );
	}
	
	// Insertar el string que comienza en i a partir del nodo p:
	private E putaux( String clave, E valor, int i, int n, NodoTrie<E> p ) {
		if( i < n ) { // Todavía queda clave por recorrer:
			// Escalo clave.charAt(i) a 0-based index array:
			int indice = ((int) clave.charAt(i)) - ((int) 'a');
			// Si no hay hijo a dónde ir, lo creo antes de seguir:
			if ( p.getHijo(indice) == null ) {
				p.setHijo( indice, new NodoTrie<E>(p) );
			}
			// Sigo insertando en el hijo clave.charAt(i):
			return putaux( clave, valor, i+1, n, p.getHijo(indice) );
		} 
		else { // i == n: Se terminó la clave… acá va el valor.
			E imagenVieja = p.getImagen(); // Salvo imagen vieja
			p.setImagen( valor ); // Seteo imagen nueva
			return imagenVieja; // Retorno imagen vieja
		}
	}
	
	public E get( String clave ) {
		// Recorro la clave desde el índice 0 y el árbol desde la raíz:
		return getaux( clave, 0, clave.length(), raiz );
	}
	
	private E getaux( String clave, int i, int n, NodoTrie<E> p ) {
		if( i == n )  {// Si se terminó la clave, en el nodo actual p está el valor en el campo imagen:
			return raiz.getImagen();
		}
		else { // No estoy al final de la clave, entonces sigo buscando:
			// Escalo clave.charAt(i) a 0-based index array:
			int indice = (int) clave.charAt(i) - (int) 'a';
			// Si La clave no existe en el árbol trie porque se me terminó el árbol
			// a pesar de que la clave sigue, retorno null:
			if( p.getHijo(indice) == null ) {
				return null;
			}
			// Busco el resto de la clave en el hijo charAt(i):
			return getaux( clave, i+1, n, p.getHijo(indice) );
		}
	}
	
	public E remove( String clave ) throws InvalidKeyException {
		// Busco clave a partir de carácter 0 y árbol a partir de nodo raíz.
		// Si removeaux sale con excepción, la propago:
		return removeaux( clave, 0, clave.length(), raiz, 0 );
	}
	private E removeaux( String clave, int i, int n, NodoTrie<E> p, int indiceP ) throws InvalidKeyException {
		E toRet = null;
		if( i == n ) { // Llegué al final de la clave, si no hay imagen, la clave no estaba:
			if( p.getImagen() == null ) {
				throw new InvalidKeyException( "Clave inexistente ");
			}
			toRet = raiz.getImagen(); // Si la clave estaba, salvo el valor para retornarlo:
			raiz.setImagen( null ); // Borro la imagen
		} 
		else { // No estoy en el final de la clave
			int indice = (int) clave.charAt(i) - (int) 'a'; // Calculo desplazamiento en arreglo hijos
			if( p.getHijo(indice) == null ) { // Si se terminó el árbol, la clave no está
				throw new InvalidKeyException( "Clave inexistente ");
			}
			// Borro en el hijo indice:
			toRet = removeaux( clave, i+1, n, p.getHijo( indice ), indice );
		}
		// Chequear si el nodo quedó todo nulo: todas las componentes son nulas e imagen también.
		if( todoNulo( p ) ) { // Desconectar este nodo salvo que sea la raiz
			if( p != raiz ) { 
				p.getPadre().setHijo( indiceP, null ); p.setPadre( null ); 
			}
		} 
		return toRet; 
	}
	
	private boolean todoNulo(NodoTrie<E> n) {
		return n.getImagen()==null && n.getPadre()==null;
	}
	
	
	
	//clase NodoTrie
	private class NodoTrie<E> {
		protected E imagen;
		protected NodoTrie<E> [] hijos;
		protected NodoTrie<E> padre;
		
		public NodoTrie(NodoTrie<E> p) {
			hijos = new NodoTrie[26];
			imagen = null; 
			padre = p; 
		}
		
		public void setImagen(E imagen) {
			this.imagen = imagen; 
		}
		public E getImagen() { 
			return imagen; 
		}
		public void setHijo(int i, NodoTrie<E> hijo ) { 
			hijos[i] = hijo; 
		}
		public NodoTrie<E> getHijo(int i) { 
			return hijos[i]; 
		}
		public void setPadre( NodoTrie<E> padre ) { 
			this.padre = padre; 
		}
		public NodoTrie<E> getPadre() { 
			return padre; 
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isEmpty() {
		return size==0;
	}


	@Override
	public Iterable<String> keys() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterable<E> values() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterable<Entry<String, E>> entries() {
		// TODO Auto-generated method stub
		return null;
	}
}
