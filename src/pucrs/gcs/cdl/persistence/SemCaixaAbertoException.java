package pucrs.gcs.cdl.persistence;

public class SemCaixaAbertoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6885449650347237922L;

	public SemCaixaAbertoException() {
		super("Não há caixa aberto!");
	}
}
