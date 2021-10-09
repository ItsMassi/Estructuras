package TDADiccionario;

/**
 * Modela la excepción de clave inválida de un diccionario.
 * @author Juan Ignacio Sánchez.
 */
public class InvalidKeyException  extends Exception {
	
	/**
	 * Inicializa una excepción de clave inválida de un diccionario con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public InvalidKeyException (String msg) {
		super(msg);
	}
}
