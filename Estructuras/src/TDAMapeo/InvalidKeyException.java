package TDAMapeo;

/**
 * Modela la excepci�n de clave inv�lida de un mapeo.
 * @author Juan Ignacio S�nchez.
 */
public class InvalidKeyException  extends Exception {
	
	/**
	 * Inicializa una excepci�n de clave inv�lida de un mapeo con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public InvalidKeyException (String msg) {
		super(msg);
	}
}
