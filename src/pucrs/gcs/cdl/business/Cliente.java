package pucrs.gcs.cdl.business;

public class Cliente {
	private String nome;
	private int idade;
	private String cpf;
	private boolean genero;
	private boolean socio;
	private String numSocio;
	
	//private static final boolean MASCULINO = false;
	//private static final boolean FEMININO = true;
	
	public Cliente(String nome, int idade, String cpf, boolean genero) {
		this(nome, idade, cpf, genero, false, "");
	}
	
	public Cliente(String nome, int idade, String cpf, boolean genero, boolean socio, String numSocio) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.genero = genero;
		this.socio = socio;
		this.numSocio = numSocio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean getGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	public boolean isSocio() {
		return socio;
	}

	public void setSocio(boolean socio) {
		this.socio = socio;
	}

	public String getNumSocio() {
		return numSocio;
	}

	public void setNumSocio(String numSocio) {
		this.numSocio = numSocio;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", idade=" + idade + ", cpf=" + cpf + ", genero=" + genero + ", socio=" + socio
				+ ", numSocio=" + numSocio + "]";
	}
	
	
}
