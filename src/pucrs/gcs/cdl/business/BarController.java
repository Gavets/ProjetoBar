package pucrs.gcs.cdl.business;

import java.io.BufferedWriter;
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
	
	public boolean clienteEntra(Cliente cliente) {
		try {
			return DbConnection.clienteEntra(cliente);
		} catch (SQLException | ClienteJaNoBarException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return false;
	}
	
	public boolean clienteSai(Cliente cliente) {
		try {
			return DbConnection.clienteSai(cliente);
		} catch (SQLException | ClienteForaDoBarException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return false;
	}
	
	public void cadastraCliente(Cliente cliente) {
		try {
			DbConnection.putCliente(cliente);
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void removeCliente() {
		
	}
	
	public void alteraCliente() {
		
	}
	
	public List<Cliente> consultaClientes() {
		try {
			return DbConnection.getClientes();
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return Collections.emptyList();
	}
	
	public List<Cliente> consultaClientesNoBar() {
		try {
			return DbConnection.getClientesNoBar();
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return Collections.emptyList();
	}
	
	public boolean clienteEstaNoBar(String cpf) {
		try {
			return DbConnection.isClienteNoBar(cpf);
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		return false;
	}
	
	public double consultaPorcentagemFeminino() {
		try {
			int totalClientes = DbConnection.countClientesNoBar();
			int totalFem = DbConnection.countClientesNoBar("genero = TRUE");
			return totalFem / totalClientes * 100;
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
		return 0;
	}
	
	public double consultaPorcentagemSocios() {
		try {
			int totalClientes = DbConnection.countClientesNoBar();
			int totalSocios = DbConnection.countClientesNoBar("socio = TRUE");
			return totalSocios / totalClientes * 100;
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
		
		return 0;
	}
	
	public void exportaClientes(String file) {
		try {
			List<Cliente> clientes = DbConnection.getClientesNoBar();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("Nome,Idade,CPF,Gênero,Sócio,Número de sócio");
			for(Cliente c : clientes) {
				writer.write(String.format(
						"%s,%d,%s,%s,%s,%s,%s",
						c.getNome(), c.getIdade(), c.getCpf(), c.getGenero() ? "Feminino" : "Masculino", c.isSocio() ? "Sim" : "Não", c.getNumSocio()
						));
			}
			writer.close();
		} catch (SQLException | IOException e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
}
