package TDAConjunto;
import java.util.Iterator;

import TDALista.*;

public class Tester {
	public static void main(String[] args) {
		Conjunto<Integer> c=new ConjuntoConArreglo<Integer>();
		c.agregar(1);
		c.agregar(2);
		c.agregar(3);
		c.agregar(4);
		/*Conjunto<Integer> c2=new ConjuntoConArreglo<Integer>();
		c2.agregar(1);
		c2.agregar(2);
		c2.agregar(3);
		c2.agregar(4);
		c.union(c2);*/
		/*Iterator<Integer> iterator=c.iterator();
		ElementIterador<Integer> elem=(ElementIterador<Integer>) iterator;
		while(elem.hasNext()) {
			System.out.print(elem.next()+" ");
		}		*/
	}
}
