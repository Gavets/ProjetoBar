package pucrs.gcs.cdl.Tests;


import org.junit.Test;
import pucrs.gcs.cdl.business.BarController;
import pucrs.gcs.cdl.business.Cliente;

import java.util.List;

import static org.junit.Assert.*;

public class BarControllerTest {

    @Test
    public void clienteEntra() {
        String cpf = "70558679242";
        boolean aux = BarController.clienteEntra(cpf);

        assertTrue(aux);
    }


    @Test
    public void clienteSai() {
        String cpf = "70558679242";
        boolean aux = BarController.clienteSai(cpf);

        assertTrue(aux);

    }

    @Test
    public void consultaClientes() {
        List<Cliente> aux = BarController.consultaClientes();

        assertFalse(aux.isEmpty());
    }

    @Test
    public void consultaClientesNoBar() {
        List<Cliente> aux = BarController.consultaClientes();

        assertFalse(aux.isEmpty());
    }

    @Test
    public void clienteExists() {

        boolean aux = BarController.clienteExists("54581431247");

        assertTrue(aux);

    }

    @Test
    public void clienteEstaNoBar() {
        boolean aux = BarController.clienteEstaNoBar("54581431247");

        assertTrue(aux);
    }

    @Test
    public void consultaTotalClientesNoBar() {
        int aux = BarController.consultaTotalClientesNoBar();

        assertEquals(8, aux);
    }

    @Test
    public void consultaPorcentagemFeminino() {
        double aux = BarController.consultaPorcentagemFeminino();

        assertEquals(57.1, aux, 0.1);
    }

    @Test
    public void consultaPorcentagemSocios() {
        double aux = BarController.consultaPorcentagemSocios();

        assertEquals(14.3, aux, 0.1);
    }
}
