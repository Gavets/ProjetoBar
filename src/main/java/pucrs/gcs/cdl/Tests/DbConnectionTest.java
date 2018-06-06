package pucrs.gcs.cdl.Tests;

import org.junit.Test;
import pucrs.gcs.cdl.business.Cliente;
import pucrs.gcs.cdl.persistence.DbConnection;

import java.util.List;

import static org.junit.Assert.*;

public class DbConnectionTest {

    @Test
    public void getClientes() {
        boolean aux = true;
        try {
            DbConnection.getClientes();
        }catch (Exception e){
            aux = false;
        }

        assertEquals(true, aux);
    }

    @Test
    public void getClientes1() {
        List<Cliente> aux = null;
        try {
            aux = DbConnection.getClientes();
        }catch (Exception e){
        }

        assertEquals(false, aux.isEmpty());
    }

    @Test
    public void getClientesNoBar() {
        boolean aux = true;
        try {
            DbConnection.getClientes();
        }catch (Exception e){
            aux = false;
        }

        assertEquals(true, aux);
    }

    @Test
    public void getClientesNoBar1() {
        List<Cliente> aux = null;
        try {
            aux = DbConnection.getClientes();
        }catch (Exception e){
        }

        assertEquals(false, aux.isEmpty());
    }

    @Test
    public void putCliente() {
        boolean aux;
        try {
          aux = DbConnection.putCliente(new Cliente("jose", 19, "266.610.847-31", false));
       }catch (Exception e){
           aux = false;
       }

       assertEquals(true, aux);
    }
}
