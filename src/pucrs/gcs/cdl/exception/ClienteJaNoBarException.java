package pucrs.gcs.cdl.exception;

public class ClienteJaNoBarException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5309093003712929553L;

	public ClienteJaNoBarException() {
		super("O cliente já está no bar!");
	}
}
