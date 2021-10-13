package TrabajoPractico6;
import TDAArbol.*;
public class testerPrint<E> extends ArbolEnlazado<E>{
	//falta pulir
	public testerPrint() {
		super();
	}
	
	public void printHijosShell() throws EmptyTreeException, InvalidPositionException {
		String orden = "│";
		if(this.isEmpty()) {
			throw new EmptyTreeException("El arbol no debe ser nulo");
		}
		System.out.println(raiz.element());
		for(Position<E> p : raiz.getHijos()) {
			TNodo<E> check = checkPosition(p);
			System.out.println("├" + check.element());
			if(!check.getHijos().isEmpty()) {printHijos(p, orden);}
		}
	}
	
	private void printHijos(Position<E> k, String o) throws InvalidPositionException {
		String orden = o+"";
		TNodo<E> pos = checkPosition(k);
		for(Position<E> p : pos.getHijos()) {
			TNodo<E> check = checkPosition(p);
			System.out.println(orden + "└"+check.element());
			//System.out.println(check.getHijos().isEmpty());
			if(!check.getHijos().isEmpty()) {printHijos(p, orden+" ");}
		}
	}
	
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
	
	

	public static void main(String[] args) {
Tree<Integer> arbol = new testerPrint<Integer>(); //Creo un arbol de enteros
		
		try {
			arbol.createRoot(1);
			Position<Integer> raiz = arbol.root();
			
			
			// Momento de armar el arbol
			// Debo agregarle hijos a la raiz, en este caso es el 1
			// En especifico 2 3 4
			
			Position<Integer> h2 = arbol.addFirstChild(raiz, 2);
			Position<Integer> h3 = arbol.addFirstChild(raiz, 3);
			Position<Integer> h4 = arbol.addFirstChild(raiz, 4);
			
			//Agrego hijos a h2
			Position<Integer> h5 = arbol.addFirstChild(h2, 5);
			Position<Integer> h6 = arbol.addFirstChild(h2, 6);
			
			//Agregp hijos a h3
			Position<Integer> h7 = arbol.addFirstChild(h3, 7);
			
			//Agrego hijos a h7
			Position<Integer> h8 = arbol.addFirstChild(h7, 8);
			Position<Integer> h9 = arbol.addFirstChild(h7, 9);
			
			Position<Integer> h10 = arbol.addFirstChild(h9, 10);
			Position<Integer> h11 = arbol.addFirstChild(h9, 11);
			Position<Integer> buscar = new TNodo<Integer>(11, null);
			arbol.printHijosShell();
		} catch (InvalidOperationException | EmptyTreeException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
