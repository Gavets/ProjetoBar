package BarSource;

public class App {

    private static IOHandler io;

    public static boolean registrarEntrada(Bar bar, Client c)
    {
       boolean result = bar.registrarEntrada(c);

       if(result)
       {
           io.log("Um novo cliente entrou no bar: ");
           io.log(c.toString());
           io.log("====================================");
       }

       return result;
    }

    public static boolean tornarSocio(Bar bar, Client c, int id)
    {
        boolean result = bar.makeSocio(c, id);

        if(result)
        {
            io.log("Um cliente tornou-se socio do bar: ");
            io.log(c.toString());
            io.log("====================================");
        }

        return result;
    }

    public static boolean retirarSocio(Bar bar, int id)
    {
        Client c = bar.buscarPorId(id);
        boolean result = bar.removeSocio(id);

        if(result)
        {
            io.log("Um cliente desasociou-se do bar: ");
            io.log(c.toString());
            io.log("====================================");
        }

        return result;
    }

    public static boolean registrarSaida(Bar bar, Client c)
    {
        io.log("O cliente [" + c.getNome() + "][" + c.getCpf() + "] saiu do bar.");

        return bar.registrarSaida(c.getCpf());
    }

    public static void main(String[] args)
    {
       try
       {
           io = IOHandler.CreateLogSystem("C:\\Users\\Douglas\\Desktop\\GCS\\log.txt");

           Client c1 = new Client("Douglas", "000.000.000-00", 13, false);
           Bar b = new Bar();

           registrarEntrada(b, c1);
           tornarSocio(b, c1, 12);
           retirarSocio(b, 12);
           registrarSaida(b, c1);

           io.save();

       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
    }
}
