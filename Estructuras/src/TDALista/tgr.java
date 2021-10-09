package TDALista;

public class tgr {

	public static void main(String[] args) {
		ListaCircularmenteEnlazada<Integer> l=new ListaCircularmenteEnlazada<Integer>();
		Nodo<Integer> n1= new Nodo<Integer> (1);
		Nodo<Integer> n2= new Nodo<Integer> (2);
		Nodo<Integer> n3= new Nodo<Integer> (3);
		Nodo<Integer> n4= new Nodo<Integer> (4);
		l.agregar(n1);
		l.agregar(n2);
		l.agregar(n3);
		//l.agregar(n4);
		System.out.println(l.toString());
	}

}
