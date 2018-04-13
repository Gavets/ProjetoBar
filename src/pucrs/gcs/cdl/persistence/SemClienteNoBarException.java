package pucrs.gcs.cdl.persistence;

public class SemClienteNoBarException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2298641847574468087L;

	public SemClienteNoBarException() {
		super("O cliente não está no bar!");
	}
}
