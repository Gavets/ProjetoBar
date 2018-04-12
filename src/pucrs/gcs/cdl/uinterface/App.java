package pucrs.gcs.cdl.uinterface;

import java.util.List;

import pucrs.gcs.cdl.business.CadClientes;
import pucrs.gcs.cdl.business.Cliente;

public class App {
	public static void main(String[] args) {
		CadClientes cad = new CadClientes();
		//Cliente c = new Cliente("Fulano", 18, "12345678901", false);
		//cad.cadastraCliente(c);
		
		List<Cliente> clientes = cad.consultaClientes();
		System.out.println(clientes.toString());
	}

}
