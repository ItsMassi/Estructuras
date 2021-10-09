package TDADiccionario;

/**
 * Modela la excepci�n de entrada inv�lida de un diccionario.
 * @author Juan Ignacio S�nchez.
 */
public class InvalidEntryException  extends Exception {
	
	/**
	 * Inicializa una excepci�n de entrada inv�lida de un diccionario con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public InvalidEntryException (String msg) {
		super(msg);
	}
}
