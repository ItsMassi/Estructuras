package TDAArbol;

/**
 * Modela la excepcion de violaci�n de l�mites de un �rbol.
 * @author Juan Ignacio S�nchez.
 */
public class BoundaryViolationException extends Exception {
	
	/**
	 * Inicializa una excepci�n de violaci�n de l�mites de un �rbol con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public BoundaryViolationException (String msg) {
		super(msg);
	}
}
