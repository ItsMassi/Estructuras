package TDAMapeo;
import TDALista.*;
public class Tester {

	public static void main(String[] args) {
		MapeoConAVL<Integer,Integer> m=new MapeoConAVL<Integer,Integer>();
		//MapeoConABB<Integer,Integer> m1=new MapeoConABB<Integer,Integer>();
		try {
			m.put(1,2);
			System.out.println("aaa"+m.put(4,2));
			m.put(3,2);
			m.put(6,2);
			m.put(9,2);
			System.out.println("bbb"+m.put(4,1));
			System.out.println("bbb"+m.put(4,4));
			m.put(2,2);
			m.remove(1);
		/*	m1.put(1,2);
			m1.put(4,2);
			m1.put(3,2);
			m1.put(6,2);
			m1.put(9,2);
			m1.put(4,1);
			m1.put(2,2);*/
			/*Iterable<Entry<Integer,Integer>> e=m.entries();
			for(Entry<Integer,Integer> en:e) {
				System.out.print("("+en.getKey()+","+en.getValue()+")" );
			}
			System.out.println();
			System.out.println("Encontro "+m.get(1));
			/*Iterable<Entry<Integer,Integer>> e1=m1.entries();
			for(Entry<Integer,Integer> en:e1) {
				System.out.print("("+en.getKey()+","+en.getValue()+")" );
			}*/
		}
		catch(InvalidKeyException e) {
			e.printStackTrace();
		}
		

	}

}
