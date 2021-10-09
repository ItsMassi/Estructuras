package TDAArbol;

/**
 * Modela la excepcion de violación de límites de un árbol.
 * @author Juan Ignacio Sánchez.
 */
public class BoundaryViolationException extends Exception {
	
	/**
	 * Inicializa una excepción de violación de límites de un árbol con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public BoundaryViolationException (String msg) {
		super(msg);
	}
}
