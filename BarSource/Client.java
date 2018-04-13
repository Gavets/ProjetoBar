package BarSource;

public class Client
{
    private String nome;
    private String cpf;
    private int idade;
    private int numero;
    private boolean genero;  // false para homens - true para mulheres
    

    public Client(String nome, String cpf, int idade, boolean genero)
    {
        this.nome = nome;
        setCpf(cpf);
        this.idade = idade;
        this.genero = genero;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public int getIdade() { return idade; }
    public boolean getGenero() { return genero; }

    private void setCpf(String cpf)
    {
        if(cpf.matches("([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})"))
        {
            this.cpf = cpf;
        }
        else
        {
            throw new IllegalArgumentException("Irregular CPF Exception");
        }
    }

    @Override
    public int hashCode()
    {
        return this.cpf.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof Client && this.cpf.equals(((Client) obj).getCpf()));
    }

    public String toString()
    {
        return "[Nome: " + nome +
                "]\n [CPF:" + cpf +
                "]\n [Idade: " + idade +
                "]\n [Gênero: " + (genero == true ? "Mulher]" : "Homem]");
    }
}