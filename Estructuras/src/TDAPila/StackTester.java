package TDAPila;

public class StackTester {

	public static void main(String[] args) {
		//try {
			//STACK CON ARREGLOS
			/*Pila_arreglo<Integer> p =new Pila_arreglo<Integer>(4);
			System.out.println("La pila esta vacia: "+p.isEmpty());
			p.push(1);
			p.push(2);	
			p.push(3);
			p.push(4);
			p.push(5);
			
			System.out.println("La pila esta vacia: "+p.isEmpty());
			System.out.println("El tamaño de la pila es : "+p.size());
			System.out.println(p.toString());
            p.pop();
            System.out.println(p.toString());
			System.out.println("El tamaño de la pila es : "+p.size());
			p.invertir();
			System.out.println(p.toString());
			
			//STACK CON ENLACES
			Pila_con_enlaces<Nodo> p1=new Pila_con_enlaces<Nodo>();
			//System.out.println("La cola esta vacia: "+p1.isEmpty());
			Nodo <Integer> n1= new Nodo<Integer> (1,null);
			Nodo <Integer> n2= new Nodo<Integer> (2,n1);
			Nodo <Integer> n3= new Nodo<Integer> (3,n2);
			Nodo <Integer> n4= new Nodo<Integer> (4,n3);
			Nodo <Integer> n5= new Nodo<Integer> (5,n4);
			p1.push(n1);
			p1.push(n2);
			p1.push(n3);
			p1.push(n4);
			p1.push(n5);
			/*System.out.println("La cola esta vacia: "+p1.isEmpty());
			System.out.println("El tamaño de la cola es : "+p1.size());
			System.out.println("El tope de la cola es: "+p1.top());
			System.out.println(p1.toString());
			/*p1.pop();
			
			System.out.println("El tamaño de la cola es : "+p1.size());
			p1.pop();
			System.out.println("El tope de la cola es: "+p1.top());*/
			
		/*}
		catch (EmptyStackException e) {
			System.out.println(e.getMessage());
		}
		catch (FullStackException e) {
			System.out.println(e.getMessage());
		}*/
	}

}
