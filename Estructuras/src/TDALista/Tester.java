package TDALista;

public class Tester {

	public static void main(String[] args) {
		try {
			ListaDoblementeEnlazada<Integer> l=new ListaDoblementeEnlazada<Integer>();
			l.addFirst(4);
			l.addFirst(3);
			l.addFirst(2);
			l.addFirst(1);
			System.out.println("Lista");
			DNodo<Integer> aux= (DNodo<Integer>) l.first();
			for(int i=0;i<l.size();i++) {
				System.out.print(aux.element()+" - ");
				aux=aux.getSiguiente();
			}
			System.out.println();
		//	l.invertir();
			System.out.println("Lista invertida");
			l.invertir();
			aux= (DNodo<Integer>) l.first();
			for(int i=0;i<l.size();i++) {
				System.out.print(aux.element()+" - ");
				aux=aux.getSiguiente();
			}
			/*//////////////////////////////////////////////// CLONE
			ListaSimplementeEnlazada<Integer> l2=new ListaSimplementeEnlazada<Integer>();
			l2.addFirst(4);
			l2.addFirst(3);
			l2.addFirst(2);
			l2.addFirst(1);
			ListaSimplementeEnlazada<Integer> l3=l2.clone();
			System.out.println(l2==l3);*/
			
			ListaSimplementeEnlazada<Integer> l4=new ListaSimplementeEnlazada<Integer>();
			l4.addFirst(4);
			l4.addFirst(3);
			l4.addFirst(2);
			l4.addFirst(1);
			System.out.println();
			System.out.println("Lista");
			Nodo<Integer> nodo=(Nodo<Integer>)l4.first();
			for(int i=0;i<l4.size();i++) {
				System.out.print(nodo.element()+" - ");
				nodo=nodo.getSiguiente();
			}
			System.out.println();
			System.out.println("Lista sin 3 y 4");
			l4.eliminarConsecutivos(3, 4);
			nodo=(Nodo<Integer>)l4.first();
			for(int i=0;i<l4.size();i++) {
				System.out.print(nodo.element()+" - ");
				nodo=nodo.getSiguiente();
			}
			
			ListaDoblementeEnlazada<Integer> l5=new ListaDoblementeEnlazada<Integer>();
			l5.addFirst(5);		
			l5.addFirst(4);
			l5.addFirst(3);
			l5.addFirst(2);
			l5.addFirst(1);
			System.out.println();
			System.out.println("Lista");
			DNodo<Integer> aux5= (DNodo<Integer>) l5.first();
			for(int i=0;i<l5.size();i++) {
				System.out.print(aux5.element()+" - ");
				aux5=aux5.getSiguiente();
			}
			System.out.println();
			/*ListaDoblementeEnlazada<Integer> l6=(ListaDoblementeEnlazada<Integer>)l5.zigzag();
			System.out.println("Lista zigzag");
			aux5= (DNodo<Integer>) l6.first();
			for(int i=0;i<l6.size();i++) {
				System.out.print(aux5.element()+" - ");
				aux5=aux5.getSiguiente();
			}*/
		}
		catch (EmptyListException e) {}
		
	}

}
