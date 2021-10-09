package TDAConjunto;

import java.util.Iterator;
import TDALista.*;

public class ConjuntoConArreglo<E> implements Conjunto<E> {
	protected E[] arreglo;
	protected int n;
	
	public ConjuntoConArreglo() {
		arreglo=(E []) new Object[10];
		n=0;
	}
	
	@Override
	public void agregar(E item) {
		System.out.println("inserto");
		arreglo[n]=item;
		n++;
	}

	@Override
	public void eliminar(E item) {
		boolean encontro=false;
		for (int i=0;i<n && !encontro;i++) {
			if (arreglo[i]==item) {
				arreglo[i]=null;//me falta correr todo para la izquierda
				moverAIzquierda(i);
				n--;
				encontro=true;
			}
		}
	}

	private void moverAIzquierda(int j) {
		for (int i=j;i<n-1;i++) {
			arreglo[i]=arreglo[i+1];
		}
		arreglo[n-1]=null;
		n--;
	}
	@Override
	public boolean pertenece(E item) {
		boolean encontro=false;
		for (int i=0;i<n && !encontro;i++) {
			if (arreglo[i]==item) {
				encontro=true;
			}
		}
		return encontro;
	}

	@Override
	public void union(Conjunto<E> c) {
		Iterator<E> iterador=c.iterator();//guardo los elementos del conjunto c
		E actual;
		while(iterador.hasNext()) {//por cada elemento del conjunto c
			actual=iterador.next();
			if (!pertenece(actual)) {//si no pertence, lo inserto en el conjunto actual
				arreglo[n]=actual;
				n++;
			}
		}
	}
	
	@Override
	public void interseccion(Conjunto<E> c) {
		for(E item:arreglo) {//por cada elemento del conjunto actual
			if (!c.pertenece(item)) {//si item no pertenece al conjunto c, lo elimino
				eliminar(item);
			}
		}
	}
	
	public Iterator<E> iterator() {
		PositionList<E> lista=new ListaDoblementeEnlazada<E>();
		for (E item:arreglo) {
			lista.addLast(item);
		}
		return lista.iterator();
	}

}
