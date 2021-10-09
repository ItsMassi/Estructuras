package TDAMapeo;

/**
 * Modela la excepción de clave inválida de un mapeo.
 * @author Juan Ignacio Sánchez.
 */
public class InvalidKeyException  extends Exception {
	
	/**
	 * Inicializa una excepción de clave inválida de un mapeo con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public InvalidKeyException (String msg) {
		super(msg);
	}
}
