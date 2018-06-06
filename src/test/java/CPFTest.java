package pucrs.gcs.cdl.Tests;


import org.junit.Test;
import pucrs.gcs.cdl.business.CPF;

import static org.junit.Assert.*;

public class CPFTest {

    @Test
    public void isValidCPF() {
        boolean aux = CPF.isValidCPF("02044556006");
        assertEquals(true, aux);
    }

    @Test
    public void isValidCPF1() {
        boolean aux = CPF.isValidCPF("02044556005");
        assertEquals(false, aux);
    }
}
