package pucrs.gcs.cdl.Tests;


import org.junit.Before;
import pucrs.gcs.cdl.business.BarController;
import pucrs.gcs.cdl.business.Cliente;
import pucrs.gcs.cdl.uinterface.App;

import java.util.List;

import static org.junit.Assert.*;

public class BarControllerTest {

    @Before
    public void setUp(){
        App.main(new String[0]);
    }

    @org.junit.Test
    public void clienteEntra() {
        String cpf = "02044556006";
        boolean aux = BarController.clienteEntra(cpf);

        assertEquals(true, aux);
    }


    @org.junit.Test
    public void clienteSai() {
        String cpf = "54581431247";

        boolean aux = BarController.clienteSai(cpf);

        assertEquals(true, aux);

    }

    @org.junit.Test
    public void consultaClientes() {
        List<Cliente> aux = BarController.consultaClientes();

        assertEquals(false, aux.isEmpty());
    }

    @org.junit.Test
    public void consultaClientesNoBar() {
        List<Cliente> aux = BarController.consultaClientes();

        assertEquals(false, aux.isEmpty());
    }

    @org.junit.Test
    public void clienteExists() {

        boolean aux = BarController.clienteExists("54581431247");

        assertEquals(true, aux);

    }

    @org.junit.Test
    public void clienteEstaNoBar() {
        boolean aux = BarController.clienteEstaNoBar("54581431247");

        assertEquals(true, aux);
    }

    @org.junit.Test
    public void consultaTotalClientesNoBar() {
        int aux = BarController.consultaTotalClientesNoBar();

        assertEquals(7, aux);
    }

    @org.junit.Test
    public void consultaPorcentagemFeminino() {
        double aux = BarController.consultaPorcentagemFeminino();

        assertEquals(57.1,aux);
    }

    @org.junit.Test
    public void consultaPorcentagemSocios() {
        double aux = BarController.consultaPorcentagemSocios();

        assertEquals(14.3, aux);
    }
}
