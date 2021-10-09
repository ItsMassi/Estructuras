package TDAConjunto;
import java.util.Comparator;
import java.util.Iterator;
import TDALista.*;
import TDAABB.*;

public class ConjuntoConABB<E extends Comparable<E>> implements Conjunto<E> {
	protected ArbolBinarioBusqueda<E> abb;
	protected Comparator <E> comparador;
	protected int n;
	
	public ConjuntoConABB() {
		abb=new ArbolBinarioBusqueda<E>();
		n=0;
	}

	public PositionList<E> listaConMenores(E clave) {
		PositionList<E> lista=new ListaDoblementeEnlazada<E>();
		int comparacion=comparador.compare(clave, abb.getRaiz().getRotulo());
		listaConMenoresAux(lista,abb.getRaiz(),clave);
		return lista;
	}
	
	private void listaConMenoresAux(PositionList<E> l, NodoABB<E> n, E c) {
		if (n.getRotulo()==null) {//llegue a un dummy
			l.addLast(n.getParent().getRotulo());
		}
		else {
			int comparacion=comparador.compare(c, n.getRotulo());
			if (comparacion<0) {//clave < rotulo de n
				listaConMenoresAux(l,n.getLeft(),c);
			}
			else {
				if (comparacion>0) {//clave > rotulo de n
					listaConMenoresAux(l,n.getLeft(),c);
					l.addLast(n.getRotulo());
					listaConMenoresAux(l,n.getRight(),c);
					
				}
			}
		}
	}
	@Override
	public void agregar(E item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(E item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean pertenece(E item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void union(Conjunto<E> c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interseccion(Conjunto<E> c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
