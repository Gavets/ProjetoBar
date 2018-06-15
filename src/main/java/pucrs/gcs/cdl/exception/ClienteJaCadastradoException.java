package pucrs.gcs.cdl.exception;

public class ClienteJaCadastradoException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = -2398641847574468087L;

	public ClienteJaCadastradoException() {
		super("O cliente ja esta cadastrado!");
	}
}
