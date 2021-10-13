package TrabajoPractico6;
import TDAArbol.*;
public class Ejercicio3 {
	
	Ejercicio3(){}
	
	public static void main(String[] args) {

		/*try {
			Tree<Integer> arbol = new ArbolEnlazado<Integer>(); //Creo un arbol de enteros
			
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
			
			
			System.out.println("Check size: "+arbol.size()); // 9
				System.out.println();
			System.out.println("Check isEmpty(): "+arbol.isEmpty()); // false
				System.out.println();
			System.out.println("Check root: "+arbol.root().element()); //1	
				System.out.println();
			System.out.println("Check isRoot(9) : "+arbol.isRoot(h9)); // false
				System.out.println();
			System.out.println("Check isRoot(6) : "+arbol.isRoot(h6)); // false
				System.out.println();
			System.out.println("Check isRoot(4) : "+arbol.isRoot(h4)); // false
				System.out.println();
			System.out.println("Check isRoot(2) : "+arbol.isRoot(h2)); // false
				System.out.println();
			System.out.println("Check isRoot(1) : "+arbol.isRoot(raiz)); // true
				System.out.println();
			System.out.println("Check  parent(9): "+arbol.parent(h9).element()); // 7
				System.out.println();
			System.out.println("Check  parent(8): "+arbol.parent(h8).element()); // 7
				System.out.println();
			System.out.println("Check  parent(7): "+arbol.parent(h7).element()); // 3
				System.out.println();
			System.out.println("Check  parent(6): "+arbol.parent(h6).element()); // 2
				System.out.println();
			System.out.println("Check  parent(5): "+arbol.parent(h5).element()); // 2
				System.out.println();
			System.out.println("Check  parent(2): "+arbol.parent(h2).element()); // 1
				System.out.println();
			System.out.println("Check  parent(3): "+arbol.parent(h3).element()); // 1
				System.out.println();
			System.out.println("Check  parent(4): "+arbol.parent(h4).element()); // 1
				System.out.println();
			System.out.println("Check isInternal(9): "+arbol.isInternal(h9)); // false
				System.out.println();
			System.out.println("Check isInternal(7): "+arbol.isInternal(h7)); //true	
				System.out.println();
			System.out.println("Check isExternal(2): "+arbol.isExternal(h2)); // false
				System.out.println();
			System.out.println("Check isExternal(4): "+arbol.isExternal(h4)); //true
				System.out.println();
			System.out.print("Check Children(1) en : "); // 2 3 4
				for(Position<Integer> p : arbol.children(raiz)) { 
					System.out.print(p.element()+" ");
				}
				
				System.out.println();
				System.out.println();
				
			System.out.print("Check Children(2) en : "); // 5 6
				for(Position<Integer> p : arbol.children(h2)) { 
					System.out.print(p.element()+" ");
				}	
				
				System.out.println();
				System.out.println();
				
			System.out.print("Check Children(3) en : ");// 7
				for(Position<Integer> p : arbol.children(h3)) { 
					System.out.print(p.element()+" ");
				}	
				
				System.out.println();
				System.out.println();
				
			System.out.print("Check Children(4) en : ");// nada
				for(Position<Integer> p : arbol.children(h4)) { 
					System.out.print(p.element()+" ");
				}	
				
				System.out.println();
				System.out.println();
				
			System.out.println("Check Children(arbol.root()): "+(arbol.children(arbol.root())).iterator().next().element());// 2
				
				System.out.println();
			
			System.out.print("Check addFirstChildren(4,10): ");	
			arbol.addFirstChild(h4, 10);
			
			for(Position<Integer> p : arbol.children(h4)) { 
					System.out.print(p.element()+" ");
			}
			
			System.out.println();
			System.out.println();
			
			arbol.addFirstChild(h4, 11);
			System.out.print("Check addLastChildren(4,11): ");
			for(Position<Integer> p : arbol.children(h4)) { 
					System.out.print(p.element()+" ");
			}
				
			System.out.println();
			System.out.println();
			
			System.out.print("Check addBefore(h7, h9, 12): ");
			arbol.addBefore(h7, h9, 12);
			for(Position<Integer> p : arbol.children(h7)) { 
				System.out.print(p.element()+" ");
			}
			arbol.removeExternalNode(h9);
			
			System.out.println();
			System.out.println();
			
			System.out.print("Check removeExternalNode(h9): ");
			for(Position<Integer> p : arbol.children(h7)) { 
				System.out.print(p.element()+" ");
			}
			
			System.out.println();
			System.out.println();
			
			arbol.removeInternalNode(h4);// Borro el 4
			System.out.print("Check removeInternalNode(h4): ");
			for(Position<Integer> p : arbol.children(raiz)) { 
				System.out.print(p.element()+" ");
			}
			
			System.out.println();
			System.out.println();
			
			arbol.removeExternalNode(h6);// Borro el 6
			System.out.print("Check removeExternalNode(h6): ");
			for(Position<Integer> p : arbol.children(h2)) { 
				System.out.print(p.element()+" ");
			}
		} catch (InvalidOperationException | InvalidPositionException | EmptyTreeException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*├
		 *└*/
		
		Tree<Integer> arbol = new ArbolEnlazado<Integer>(); //Creo un arbol de enteros
		
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
			Position<Integer> buscar = new TNodo<Integer>(11, null);
			Position<Integer> kekw = arbol.FindShell(buscar);
			System.out.println(kekw.element());
		} catch (InvalidOperationException | EmptyTreeException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
