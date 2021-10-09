package TDADiccionario;

public class Tester {

	public static void main(String[] args) {
		try {
			DiccionarioConHashAbierto<Character,Character> d=new DiccionarioConHashAbierto<Character,Character>();
			d.insert('A', 'B');
			d.insert('A', 'C');
			d.insert('A', 'D');
			d.insert('D', 'E');
			for (Entry<Character,Character> e:d.entries()) {
				System.out.print("("+e.getKey()+","+e.getValue()+") - ");
			}
			d.convertirAMapeo();
			System.out.println();
			for (Entry<Character,Character> e:d.entries()) {
				System.out.print("("+e.getKey()+","+e.getValue()+") - ");
			}
		}
		catch(InvalidKeyException e) {
			e.printStackTrace();
		}
	}

}
