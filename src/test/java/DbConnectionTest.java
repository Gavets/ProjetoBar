

import org.junit.Before;
import org.junit.Test;
import pucrs.gcs.cdl.business.Cliente;
import pucrs.gcs.cdl.persistence.DbConnection;
import pucrs.gcs.cdl.uinterface.App;

import java.util.List;

import static org.junit.Assert.*;

public class DbConnectionTest {

    @Before
    public void encheBar() {
        App.encheBar();
    }

    @Test
    public void getClientes() {
        boolean aux = true;
        try {
            DbConnection.getClientes();
        }catch (Exception e){
            aux = false;
        }

        assertTrue(aux);
    }

    @Test
    public void getClientes1() {
        List<Cliente> aux = null;
        try {
            aux = DbConnection.getClientes();
        }catch (Exception e){
        }

        assertFalse(aux.isEmpty());
    }

    @Test
    public void getClientesNoBar() {
        boolean aux = true;
        try {
            DbConnection.getClientes();
        }catch (Exception e){
            aux = false;
        }

        assertTrue(aux);
    }

    @Test
    public void getClientesNoBar1() {
        List<Cliente> aux = null;
        try {
            aux = DbConnection.getClientes();
        }catch (Exception e){
        }

        assertFalse(aux.isEmpty());
    }

    @Test
    public void putCliente() {
        boolean aux;
        Cliente c;
        try {
            c = new Cliente("jose", 19, "26661084731", false);
            DbConnection.removeCliente(c);
            aux = DbConnection.putCliente(c);
            DbConnection.removeCliente(c);
       }catch (Exception e){
           aux = false;
       }

        assertTrue(aux);
    }
}
