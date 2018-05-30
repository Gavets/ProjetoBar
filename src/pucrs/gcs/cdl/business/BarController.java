package pucrs.gcs.cdl.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import pucrs.gcs.cdl.exception.ClienteJaNoBarException;
import pucrs.gcs.cdl.exception.ClienteForaDoBarException;
import pucrs.gcs.cdl.persistence.DbConnection;

public class BarController {
	
	public BarController() {
		
	}
	
	public static boolean clienteEntra(String cpf) {
		try {
			return DbConnection.clienteEntra(cpf);
		} catch (SQLException | ClienteJaNoBarException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return false;
	}
	
	public static boolean clienteEntra(Cliente cliente) {
		return clienteEntra(cliente.getCpf());
	}
	
	public static boolean clienteSai(String cpf) {
		try {
			return DbConnection.clienteSai(cpf);
		} catch (SQLException | ClienteForaDoBarException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return false;
	}
	
	public static boolean clienteSai(Cliente cliente) {
		return clienteSai(cliente.getCpf());
	}
	
	public static void cadastraCliente(Cliente cliente) {
		try {
			DbConnection.putCliente(cliente);
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public static void removeCliente() {
		
	}
	
	public static void alteraCliente() {
		
	}
	
	public static List<Cliente> consultaClientes() {
		try {
			return DbConnection.getClientes();
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return Collections.emptyList();
	}
	
	public static List<Cliente> consultaClientesNoBar() {
		try {
			return DbConnection.getClientesNoBar();
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return Collections.emptyList();
	}
	
	public static boolean clienteExists(String cpf) {
		try {
			return DbConnection.clienteExists(cpf);
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return false;
	}
	
	public static boolean clienteEstaNoBar(String cpf) {
		try {
			return DbConnection.isClienteNoBar(cpf);
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return false;
	}
	
	public static int consultaTotalClientesNoBar() {
		try {
			return DbConnection.countClientesNoBar();
		} catch(SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return 0;
	}
	
	public static double consultaPorcentagemFeminino() {
		try {
			int totalClientes = DbConnection.countClientesNoBar();
			int totalFem = DbConnection.countMulheresNoBar();
			if (totalClientes==0)
				return 0.0;
			return (double) totalFem / totalClientes * 100;
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
		return 0;
	}
	
	public static double consultaPorcentagemSocios() {
		try {
			int totalClientes = DbConnection.countClientesNoBar();
			int totalSocios = DbConnection.countSociosNoBar();
			if (totalClientes==0)
				return 0.0;
			return (double) totalSocios / totalClientes * 100;
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
		return 0;
	}
	
	public static void exportaClientes(File file) {
		try {
			List<Cliente> clientes = DbConnection.getClientesNoBar();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("Nome,Idade,CPF,Gênero,Sócio,Número de sócio\n");
			for(Cliente c : clientes) {
				writer.write(String.format(
						"%s,%d,%s,%s,%s,%s\n",
						c.getNome(), c.getIdade(), c.getCpf(), c.getGenero() ? "Feminino" : "Masculino", c.isSocio() ? "Sim" : "Não", c.getNumSocio()
						));
			}
			writer.close();
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
}
