package TDAArbol;

/**
 * Modela la excepci?n de operaci?n inv?lida de un ?rbol.
 * @author Juan Ignacio S?nchez.
 */
public class InvalidOperationException extends Exception {
	
	/**
	 * Inicializa una excepci?n de operaci?n inv?lida de un ?rbol con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci?n.
	 */
	public InvalidOperationException (String msg) {
		super(msg);
	}
}


