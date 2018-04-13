package BarSource;

import java.util.HashSet;
import java.util.Set;

public class Bar {

    private Set<Client> clientes;
    private Set<Socio> socios;

    public Bar()
    {
        this.clientes = new HashSet<>();
        this.socios = new HashSet<>();
    }

    public Socio buscarPorId(int id)
    {
        return socios.stream()
                     .filter(s -> s.getId() == id)
                     .findFirst()
                     .orElse(null);
    }

    public Client buscarPorCpf(String cpf)
    {
        Client client = clientes.stream()
                                .filter(c -> c.getCpf().equals(cpf))
                                .findFirst()
                                .orElse(null);

        // Se não encontrou o cliente...
        // Busque por um socio associado ao cpf.
        if(client == null)
        {
            client = socios.stream()
                           .filter(s -> s.getCpf().equals(cpf))
                           .findFirst()
                           .orElse(null);
        }

        return client;
    }

    private int totalMulheresAssociadas()
    {
        return (int)socios.stream()
                          .filter(Client::getGenero)
                          .count();     //collect(Collectors.toSet()).size();
    }

    private int totalClientesMulheres()
    {
        return (int)clientes.stream()
                            .filter(Client::getGenero)
                            .count();     //collect(Collectors.toSet()).size();
    }

    private int getTotalWoman()
    {
        return totalMulheresAssociadas() + totalClientesMulheres();
    }

    public double getGeneroPercentual(boolean genero)
    {
        int total = totalClientes();
        double percentual = 0.0;

        if(!genero)
        {
            // Desconta o total de mulheres do total de clientes
            // Para obter o total de homens
            percentual = ((total - getTotalWoman())*100) / total;
        }
        else
        {
            int mulheres = getTotalWoman();
            percentual = (mulheres*100)/total;
        }

        return percentual;
    }

    public int numClientes() { return clientes.size(); }
    public int numSocios() { return socios.size(); }
    public int totalClientes(){ return (numClientes() + numSocios()); }

    public boolean registrarSaida(String cpf)
    {
        Client c = buscarPorCpf(cpf);

        if(c instanceof Client)
        {
            return clientes.remove(c);
        }
        else
        {
           return socios.remove(c);
        }
    }

    public boolean makeSocio(Client c, int id)
    {
        boolean result = false;
        if(c instanceof Client)
        {
            clientes.remove(c);
            Socio s = new Socio(c.getNome(), c.getCpf(), c.getIdade(), c.getGenero(), id);
            result = registrarEntrada(s);    //socios.add(s);
            c = null;
        }
        else
            throw new IllegalArgumentException("Client expected");

        return result;
    }

    public boolean removeSocio(int id)
    {
        boolean result = false;
        Socio s = buscarPorId(id);

        if(s != null)
        {
            socios.remove(s);
            Client c = new Client(s.getNome(), s.getCpf(), s.getIdade(), s.getGenero());
            result = registrarEntrada(c);    //clientes.add(c);
            s = null;
        }
        else
            throw new IllegalArgumentException("Socio expected");

        return result;
    }

    public boolean registrarEntrada(Client c)
    {
        if(c == null)
            throw new NullPointerException();

        if(c instanceof Socio)
        {
            return this.socios.add((Socio)c);
        }
        else
        {
            return this.clientes.add(c);
        }
    }


}
