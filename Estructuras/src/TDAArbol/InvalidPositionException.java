package TDAArbol;

/**
 * Modela la excepción de posición inválida de un árbol.
 * @author Juan Ignacio Sánchez.
 */
public class InvalidPositionException  extends Exception{
	
	/**
	 * Inicializa una excepción de posición inválida de un árbol con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public InvalidPositionException (String msg) {
		super(msg);
	}
}