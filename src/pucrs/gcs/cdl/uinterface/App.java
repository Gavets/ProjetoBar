package pucrs.gcs.cdl.uinterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import pucrs.gcs.cdl.business.BarController;
import pucrs.gcs.cdl.business.Cliente;

public class App {
	public static void main(String[] args) {
		BarController cad = new BarController();
		
		List<Cliente> cs = csvImport();
		for(Cliente c : cs)
			cad.cadastraCliente(c);
		
		List<Cliente> clientes = cad.consultaClientes();
		clientes.forEach(c -> System.out.println(c));
		
		Cliente cliente = clientes.get(0);
		System.out.println("Entrando cliente");
		cad.clienteEntra(cliente);
		
		System.out.println("Clientes no bar");
		cad.consultaClientesNoBar().forEach(c -> System.out.println(c.toString()));
		
		System.out.println("Saindo cliente");
		cad.clienteSai(cliente);
		
		System.out.println("Clientes no bar");
		cad.consultaClientesNoBar().forEach(c -> System.out.println(c.toString()));
	}
	
	public static List<Cliente> csvImport() {
		List<Cliente> clientes = new ArrayList<>();
		Path csvFile = Paths.get(System.getProperty("user.dir"), "db", "nomes.csv");
		try {
			List<String> lines = Files.readAllLines(csvFile);
			for(int i = 1; i < 11; i++) {
				String[] line = lines.get(i).split(",");
				clientes.add(new Cliente(line[2], Integer.parseInt(line[3]), line[4], (line[1] == "true")));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clientes;
	}

}
