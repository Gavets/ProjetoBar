


import org.junit.Before;
import org.junit.Test;
import pucrs.gcs.cdl.business.BarController;
import pucrs.gcs.cdl.business.Cliente;
import pucrs.gcs.cdl.uinterface.App;

import java.util.List;

import static org.junit.Assert.*;

public class BarControllerTest {
    String cpf = "54581431247";

    @Before
    public void encheBar() {
        App.encheBar();
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

        boolean aux = BarController.clienteExists(cpf);

        assertTrue(aux);

    }

    @Test
    public void clienteEstaNoBar() {
        BarController.clienteEntra(cpf);
        boolean aux = BarController.clienteEstaNoBar(cpf);

        assertTrue(aux);
    }

    @Test
    public void clienteSai() {
        BarController.clienteEntra(cpf);
        boolean aux = BarController.clienteSai(cpf);

        assertTrue(aux);

    }

    @Test
    public void clienteEntra() {
        BarController.clienteSai(cpf);
        boolean aux = BarController.clienteEntra(cpf);

        assertTrue(aux);
    }

    @Test
    public void consultaTotalClientesNoBar() {
        int aux = BarController.consultaTotalClientesNoBar();
        int c = BarController.consultaClientesNoBar().size();

        assertEquals(c, aux);
    }

    @Test
    public void consultaPorcentagemFeminino() {
        double aux = BarController.consultaPorcentagemFeminino();
        List<Cliente> cs = BarController.consultaClientesNoBar();
        long f = cs.stream().filter(Cliente::getGenero).count();


        assertEquals((double) f / cs.size() * 100, aux, 0.1);
    }

    @Test
    public void consultaPorcentagemSocios() {
        double aux = BarController.consultaPorcentagemSocios();
        List<Cliente> cs = BarController.consultaClientesNoBar();
        long s = cs.stream().filter(Cliente::isSocio).count();

        assertEquals((double) s / cs.size() * 100, aux, 0.1);
    }
}
