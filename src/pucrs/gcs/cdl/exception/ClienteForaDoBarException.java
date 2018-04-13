package pucrs.gcs.cdl.exception;

public class ClienteForaDoBarException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2298641847574468087L;

	public ClienteForaDoBarException() {
		super("O cliente não está no bar!");
	}
}
