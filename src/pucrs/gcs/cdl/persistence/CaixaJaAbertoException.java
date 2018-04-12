package pucrs.gcs.cdl.persistence;

public class CaixaJaAbertoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8758805779488730720L;

	public CaixaJaAbertoException() {
		super("Já existe um caixa aberto!");
	}

}
