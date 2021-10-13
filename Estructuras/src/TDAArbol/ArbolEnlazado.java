package TDAArbol;

import java.util.Iterator;

import TDALista.*;

/**
 * Define los datos y operaciones aplicables sobre un árbol enlazado.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos a almacenar en el árbol enlazado.
 */
public class ArbolEnlazado<E> implements Tree<E> {
	
	/**
	 * Nodo raíz del árbol enlazado.
	 */
	protected TNodo<E> raiz;
	
	/**
	 * Tamaño del árbol enlazado.
	 */
	protected int size;
	
	/**
	 * Inicializa un árbol vacío.
	 */
	public ArbolEnlazado() {
		raiz=null;
		size=0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return raiz==null;
	}
	
	@Override
	public Iterator<E> iterator() {
		PositionList<E> l=new ListaDoblementeEnlazada<E>();
		for (Position<E> p:positions()) {
			l.addLast(p.element());
		}
		return l.iterator();
	}
	
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l=new ListaDoblementeEnlazada<Position<E>>();
		if (size!=0) {
			pre(raiz, l);
		}
		return l;
	}
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> p=checkPosition(v);
		E aux=p.element();
		p.setElemento(e);
		return aux;
	}
	
	@Override
	public Position<E> root() throws EmptyTreeException {
		if (size==0) {
			throw new EmptyTreeException("Árbol vacío");
		}
		return raiz;
	}
	
	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> p=checkPosition(v);
		if (p==raiz) {
			throw new BoundaryViolationException("p corresponde a la raiz del árbol");
		}
		return p.getPadre();
	}
	
	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> p=checkPosition(v);
		PositionList<Position<E>> lista=new ListaDoblementeEnlazada<Position<E>>();
		for (TNodo<E> n:p.getHijos()) {
			lista.addLast(n);
		}
		return lista;
	}
	
	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo <E> nodo=checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}
	
	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo <E> nodo=checkPosition(v);
		return nodo.getHijos().isEmpty();
	}
	
	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo <E> nodo=checkPosition(v);
		return nodo.getPadre()==null;
	}
	
	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if (raiz!=null) {
			throw new InvalidOperationException("El árbol ya tiene raiz");
		}
		Position<E> padre=new TNodo<E>(e,null);
		raiz=(TNodo<E>) padre;
		size++;
	}
	
	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		if (size==0) {
			throw new InvalidPositionException("El árbol está vacío");
		}
		TNodo<E> nodo=checkPosition(p);
		TNodo<E> nuevo=new TNodo<E>(e, nodo);
		nodo.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}
	
	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if (size==0) {
			throw new InvalidPositionException("El árbol está vacío");
		}
		TNodo<E> nodo=checkPosition(p);
		TNodo<E> nuevo=new TNodo<E>(e, nodo);
		nodo.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}
	
	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException{
		TNodo<E> nuevo=null,n,hd;
		PositionList<TNodo<E>> hijos;
		TDALista.Position<TNodo<E>> pp;
		boolean encontre=false;
		try {
			if(size==0){
				throw new InvalidPositionException("Arbol vacio");
			}
			n=checkPosition(p);
			hd=checkPosition(rb);
			nuevo=new TNodo<E>(e,n);
			hijos=n.getHijos();
			if(n.getHijos().isEmpty()){
				throw new InvalidPositionException("El nodo p no es padre de rb");
			}
			pp=hijos.first();
			while(pp!=null && !encontre){//busco a rb en la lista de hijos de de p
				if(hd==pp.element()){
					encontre=true;
				}
				else{
					pp=(pp!=hijos.last() ? hijos.next(pp) : null);
				}
			}
			if(!encontre){
				throw new InvalidPositionException("El nodo p no es padre de rb");
			}
			hijos.addBefore(pp,nuevo);
			size++;
		}
		catch(EmptyListException ex) {
			ex.printStackTrace();
		}
		catch(TDALista.BoundaryViolationException ex) {
			ex.printStackTrace();
		}
		catch(TDALista.InvalidPositionException ex) {
			ex.printStackTrace();
		}
		return nuevo;
	}

	
	@Override
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> nuevo=null,n,hd;
		PositionList<TNodo<E>> hijos;
		TDALista.Position<TNodo<E>> pp;
		boolean encontre;
		try {
			if(size==0){
				throw new InvalidPositionException("Arbol vacio");
			}
			n=checkPosition(p);
			hd=checkPosition(lb);
			nuevo=new TNodo<E>(e,n);
			hijos=n.getHijos();
			if(n.getHijos().isEmpty()){
				throw new InvalidPositionException("El nodo p no es padre de rb");
			}
			encontre=false;
			pp=hijos.first();
			while(pp!=null && !encontre){//busco a lb en la lista de hijos de de p
				if(hd==pp.element()){
					encontre=true;
				}
				else{
					pp=(pp!=hijos.last() ? hijos.next(pp) : null);
				}
			}
			if(!encontre){
				throw new InvalidPositionException("El nodo p no es padre de rb");
			}
			hijos.addAfter(pp,nuevo);
			size++;
		}
		catch(TDALista.EmptyListException ex) {
			ex.printStackTrace();
		}
		catch(TDALista.BoundaryViolationException ex) {
			ex.printStackTrace();
		}
		catch(TDALista.InvalidPositionException ex) {
			ex.printStackTrace();
		}
		return nuevo;
	}
	
	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		if(!n.getHijos().isEmpty()) {
			throw new InvalidPositionException("No es un nodo externo");
		}
		removeNode(n);
	}
	
	@Override
	public void removeInternalNode (Position<E> p) throws InvalidPositionException {
		TNodo<E> n=checkPosition(p);
		if(n.getHijos().isEmpty()) {
			throw new InvalidPositionException("No es un nodo interno");
		}
		removeNode(n);
	}
	
	@Override
	public void removeNode (Position<E> p) throws InvalidPositionException {
		TNodo<E> nEliminar, padre, hijo;
		PositionList<TNodo<E>> hermanos;
		TDALista.Position<TNodo<E>> posListaHermanos;
		PositionList<TNodo<E>> hijos;
		if (size==0) {
			throw new InvalidPositionException("El árbol está vacío");
		}
		nEliminar=checkPosition(p);
		padre=nEliminar.getPadre();
		hijos=nEliminar.getHijos();
		try{
			if (nEliminar==raiz){
				if (hijos.isEmpty()){
						raiz=null;
				}
				else {
					if (hijos.size()==1){
						hijo=hijos.remove(hijos.first());
						hijo.setPadre(null);
						raiz=hijo;//establezco al hijo de la raiz como nueva raiz
					}
					else {
						throw new InvalidPositionException("No se puede eliminar raíz con hijos > 1");
					}
				}
			}
			else {
				hermanos=padre.getHijos();//hermanos de nEliminar
				posListaHermanos=hermanos.isEmpty() ? null : hermanos.first();//si hermanos no esta vacía, a posListaHermanos se le asigna el primer hermano
				while(posListaHermanos!=null && posListaHermanos.element()!=nEliminar) {//busco nEliminar
					posListaHermanos=(hermanos.last()==posListaHermanos) ? null : hermanos.next(posListaHermanos);
				}
				if (posListaHermanos==null) {//nEliminar no fue encontrada 
					throw new InvalidPositionException("La posición p no se encuentra en la lista del padre");
				}
				while(!hijos.isEmpty()) {//agrega a la lista de hermanos todos los hijos de nElimar
					hijo=hijos.remove(hijos.first());
					hijo.setPadre(padre);
					hermanos.addBefore(posListaHermanos, hijo);
				}
				hermanos.remove(posListaHermanos);
			}
			nEliminar.setPadre(null);
			nEliminar.setElemento(null);
			size--;
		}
		catch (EmptyListException e) {
			e.printStackTrace();
		}
		catch (TDALista.BoundaryViolationException e) {
			e.printStackTrace();
		}
		catch(TDALista.InvalidPositionException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Inserta un nodo de árbol y todos sus hijos a una lista de posiciones.
	 * @param v Nodo de árbol a insertar en la lista de posiciones.
	 * @param l Lista de posiciones en la que se inserta el nodo de árbol y todos sus hijos.
	 */
	private void pre(TNodo<E> v, PositionList<Position<E>> l) {
		l.addLast(v);
		for (TNodo<E> h:v.getHijos()) {
			pre(h, l);
		}
	}
	
	/**
	 * Devuelve un nodo de árbol creado a partir de la posición n.
	 * @param n Posición a partir de la cual se crea el nodo de árbol.
	 * @return Nodo de árbol creado a partir de la posición n.
	 * @throws InvalidPositionException si la posición es nula, si fue eliminada anteriormente o si no es de este árbol.
	 */
	private TNodo<E> checkPosition(Position<E> n) throws InvalidPositionException {
		TNodo<E> retorna=null;
		try {
			retorna=(TNodo<E>) n;
			if (n==null) {
				throw new InvalidPositionException("Error, posición nula.");
			}
			if (n.element()==null) {
				throw new InvalidPositionException("El elemento fue eliminado previamente.");
			}
			return retorna;
		}
		catch (ClassCastException e) {
			throw new InvalidPositionException("n no es un nodo de este árbol.");
		}
	}
	
	public Tree<E> clone(){
		Tree<E> clon = new ArbolEnlazado<E>();
		if(raiz!=null) {
			try {
				clon.createRoot(raiz.element());
				clonarRec(raiz,clon.root(),clon);
			} catch (InvalidOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EmptyTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return clon;
	}
	
	private void clonarRec(TNodo<E> nodo, Position<E> nodoClon, Tree<E> clon) {
		for(TNodo<E> hijo: nodo.getHijos()) {
			try {
				Position<E> hijoClon = clon.addLastChild(nodoClon, hijo.element());
				clonarRec(hijo,hijoClon,clon);
			} catch (InvalidPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	////////////////////////////////////////////////OPERACIONES/////////////////////////////////////////////////////////////////////////////////
	public void listar(boolean p) {
		if (size!=0) {
			listarAux(raiz,p);
		}
	}
	
	private void listarAux(TNodo<E> t,boolean p) {
		if (p) {
			System.out.print(t.element()+" - ");
			for (Position<E> pp:t.getHijos()) {
				TNodo<E> tt=(TNodo<E>)pp;
				listarAux(tt,p);
			}
		}
		else {
			for (Position<E> pp:t.getHijos()) {
				TNodo<E> tt=(TNodo<E>)pp;
				listarAux(tt,p);
			}
			System.out.print(t.element()+" - ");
		}
	}
	
	public void eliminarHojas() {
		if (size!=0 ) {
			eliminarHojasAux(raiz);
		}
	}
	
	private void eliminarHojasAux(TNodo<E> t) {
		try {
			if (t.getHijos().isEmpty()) {//si es externo
				removeNode(t);
			}
			else {
				for (Position<E> pp:t.getHijos()) {
					eliminarHojasAux((TNodo<E>) pp);
				}
			}
		}
		catch(InvalidPositionException ex) {
			ex.printStackTrace();
		}
	}
	
	public void eliminarRotulo(E r) {
		if (size!=0 ) {
			eliminarRotuloAux(raiz,r);
		}
	}
	
	private void eliminarRotuloAux(TNodo<E> t, E r) {
		try {
			if (t.element().equals(r)) {
				removeNode(t);
			}
			for (Position<E> pp:t.getHijos()) {
				eliminarRotuloAux((TNodo<E>) pp,r);
			}
		}
		catch(InvalidPositionException ex) {
			ex.printStackTrace();
		}
	}
	
	public void eliminarRotuloProfundidad(E r, int n) {
		if (!isEmpty()) {
			eliminarRotuloProfundidadAux(r,n,raiz);
		}
	}
	
	private void eliminarRotuloProfundidadAux(E r,int nivel, TNodo<E> p) {
		try {
			if (profundidad(p)==nivel) {//si esta en el nivel del parametro
				if (p.element().equals(r)) {
					removeNode(p);
				}
			}
			else {
				for(Position<E> pp:p.getHijos()) {
					eliminarRotuloProfundidadAux(r,nivel,(TNodo<E>) pp);
				}
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	public int profundidad(Position<E> p) {//Longitud del camino de la raiz de t a la posicion p
		int cont=0;
		try {
			if (!isEmpty() /*&& existePosicion(p)*/) {
				if (p==raiz) {
					cont=0;
				}
				else {
					cont=1+profundidad(parent(p));
				}
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(BoundaryViolationException e) {
			e.printStackTrace();
		}
		return cont;
	}
	
	/*private static <E> boolean existePosicion(Position<E> p1) {
		boolean existe=false;
		for(Position<E> p : positions()) {
			if (p==p1) {
				existe=true;
				break;//para que corte el for each y sea más eficiente
			}
		}
		return existe;
	}*/
	
	public void espejar() {
		try {
			if (size!=0) {
				ArbolEnlazado<E> arbol=new ArbolEnlazado<E>();//arbol en el ingreso el esepjado
				arbol.createRoot(raiz.element());
				TNodo<E> nuevaRaiz=(TNodo<E>) arbol.root();//raiz del nuevo arbol
				espejarAux(arbol,nuevaRaiz,raiz);
				raiz=nuevaRaiz;//paso la raiz del arbol espejado a la raiz del arbol original
			}
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	
	private void espejarAux(ArbolEnlazado<E> t, TNodo<E> pt, TNodo<E> p) {
		try {
			for(Position<E> pp:p.getHijos()) {
				TNodo<E> ppp=(TNodo<E>) t.addFirstChild(pt, pp.element());
				espejarAux(t,(TNodo<E>) ppp,(TNodo<E>) pp);
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	public Position<E> FindShell(Position<E> p) throws EmptyTreeException, InvalidPositionException{
		Position<E> check = null;
		Position<E> retorno = null;
		if(this.isEmpty()) {
			throw new EmptyTreeException("el arbol no puede se vacio");
		}
		if(p.element()==null) {
			throw new InvalidPositionException("el nodo no puede se vacio");
		}
		
		if(raiz.element()==p.element()) {
			return raiz;
		}
		
		if(raiz.getHijos().isEmpty()) {
			return null;
		}else {
			for(Position<E> k : raiz.getHijos()) {
				check = find(k,p);
				if(check!=null) {retorno = check;}
			}
		}
		
		return retorno;
	}
	
	private Position<E> find(Position<E> k, Position<E> p) throws InvalidPositionException{
		Position<E> retorno = null;
		TNodo<E> kn = checkPosition(k);
		if(k.element() == p.element()) {
			return k;
		}else {
			if(!kn.getHijos().isEmpty()) {
				for(Position<E> l : kn.getHijos()) {
					retorno = find(l,p);
				}
			}
		}
		return retorno;
	}

	@Override
	public void printHijosShell() throws EmptyTreeException, InvalidPositionException {
		// TODO Auto-generated method stub
		
	}

 }
