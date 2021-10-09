package TDAArbol;

import Operaciones.OperacionesConArboles;

public class Tester {
	public static void main(String[] args) {
		try {
			ArbolEnlazado<Integer> t=new ArbolEnlazado<Integer>();
			t.createRoot(0);
			TNodo<Integer> raiz=(TNodo<Integer>) t.root();
			Position<Integer> p1=t.addLastChild(raiz, 1);
			Position<Integer> p2=t.addLastChild(raiz, 2);
			Position<Integer> p3=t.addLastChild(raiz, 3);
			Position<Integer> p4=t.addLastChild(p1, 4);
			Position<Integer> p5=t.addLastChild(p1, 5);
			Position<Integer> p6=t.addLastChild(p2, 6);
			Position<Integer> p7=t.addLastChild(p3, 7);
			System.out.println("Preorden");
			t.listar(true);
			System.out.println();
			System.out.println("Postorden");
			t.listar(false);
			System.out.println();
			/*System.out.println("Elimino las hojas");
			t.eliminarHojas();*/
			System.out.println("Preorden");
			t.listar(true);
			System.out.println();
			System.out.println("Elimino los nodos con rotulo 7");
			t.eliminarRotulo(7);
			System.out.println("Preorden");
			t.listar(true);
			/*
			System.out.println();
			System.out.println("Profundidas de raiz: "+t.profundidad(p1));
			System.out.println("Elimino los nodos de rotulo 2 en el nivel 1");
			t.eliminarRotuloProfundidad(2, 1);
			t.listar(true);
			t.espejar();
			System.out.println(t.size());
			t.listar(true);*/
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
