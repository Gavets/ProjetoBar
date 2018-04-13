package BarSource;

public class Socio extends Client {

    private int id;
    public Socio(String nome, String cpf, int idade, boolean genero, int id)
    {
        super(nome, cpf, idade, genero);
        setId(id);
    }

    public int getId() { return this.id; }
    private void setId(int id)
    {
        if(id > 0)
        {
            this.id = id;
        }
        else
        {
            throw  new IllegalArgumentException("Invalid ID Exception");
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof Socio
                && ((Socio)obj).getId() == this.id);
    }

    @Override
    public int hashCode()
    {
        return Integer.toString(this.id).hashCode();
    }

    public String toString()
    {
        return super.toString() + "\n [Id: " + id + "]";
    }
}
