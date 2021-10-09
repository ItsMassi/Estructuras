package TDAPila;

public class OperacionesConPilas {
	public static void invertirArreglo(int a[]) throws EmptyStackException {
		Pila_con_enlaces <Integer> p=new Pila_con_enlaces<Integer>();
		for (int i=0;i<a.length;i++) {
			p.push(a[i]);
		}
		
		for (int i=0;i<a.length;i++) {
			if (p.isEmpty()) {
				throw new EmptyStackException("Error, la pila está vacía");
			}
			else {
				a[i]=p.pop();
			}
		}
	}
	
	
	public static <E> void invertir(Stack<E> p) throws EmptyStackException {
		if (p.isEmpty()) {
			throw new EmptyStackException("Error, la pila está vacía");
		}
		Pila_arreglo<E> pa1=new Pila_arreglo<E>();
		while(!p.isEmpty()) {
			E aux=p.pop();
			pa1.push(aux);
		}
		Pila_arreglo<E> pa2=new Pila_arreglo<E>();
		while(!pa1.isEmpty()) {
			E aux=pa1.pop();
			pa2.push(aux);
		}
		while(!pa2.isEmpty()) {
			E aux=pa2.pop();
			p.push(aux);
		}
	}
	
	public static <E> Stack<E> Aplanar (Pila_arreglo< Pila_arreglo <E>> a) throws EmptyStackException {
		Pila_con_enlaces<E> p=new Pila_con_enlaces<E>();
		while(!a.isEmpty()) {
			while(!a.top().isEmpty()) {
				p.push(a.top().pop());
			}
			a.pop();
		}
		invertir(p);
		return p;		
	}
	///////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		try {
			/* int [] a= {1,2,3,4};
			OperacionesConPilas o=new OperacionesConPilas();
			for (int i=0;i<a.length;i++) {
				System.out.print(a[i]+"-");
			}
			System.out.println();
			invertirArreglo(a);
			for (int i=0;i<a.length;i++) {
				System.out.print(a[i]+"-");
			}
			*/
			/*Stack <Integer> p=new Pila_con_enlaces<Integer>();
			p.push(1);
			p.push(2);
			p.push(3);
			p.push(4);
			invertir(p);
			while(!p.isEmpty()) {
				System.out.print(p.pop()+"-");
			}
			*/
			Pila_arreglo<Integer> p2=new Pila_arreglo<Integer>();
			p2.push(1); p2.push(2); p2.push(3); p2.push(4);
			Pila_arreglo <Pila_arreglo <Integer>> p3=new Pila_arreglo <Pila_arreglo <Integer>>();
			p3.push(p2);p3.push(p2);p3.push(p2);
			//aplanar(p3);
			while(!p3.isEmpty()) {
				System.out.print(p3.pop()+"-");
			}
			
		}
		catch (EmptyStackException e) {}
	}
}
