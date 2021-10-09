package TDADiccionario;

/**
 * Modela la excepción de entrada inválida de un diccionario.
 * @author Juan Ignacio Sánchez.
 */
public class InvalidEntryException  extends Exception {
	
	/**
	 * Inicializa una excepción de entrada inválida de un diccionario con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public InvalidEntryException (String msg) {
		super(msg);
	}
}
