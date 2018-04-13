package pucrs.gcs.cdl.business;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import pucrs.gcs.cdl.persistence.DbConnection;

public class BarController {
	
	public BarController() {
		
	}
	
	public void cadastraCliente(Cliente cliente) {
		try {
			DbConnection.putCliente(cliente);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeCliente() {
		
	}
	
	public void alteraCliente() {
		
	}
	
	public List<Cliente> consultaClientes() {
		try {
			return DbConnection.getClientes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Cliente consultaPorCpf(String cpf) {
		try {
			return DbConnection.getClientes("cpf = " + cpf).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public double consultaPorcentagemGenero() {
		List<Cliente> clientes = consultaClientes();
		if(clientes == null)
			return -1;
		
		Long count = clientes.stream().filter(c -> c.getGenero()).collect(Collectors.counting());
		return count / clientes.size() * 100;
	}
	
	public double consultaPorcentagemSocios() {
		List<Cliente> clientes = consultaClientes();
		if(clientes == null)
			return -1;
		
		Long count = clientes.stream().filter(c -> c.isSocio()).collect(Collectors.counting());
		return count / clientes.size() * 100;
	}
	
	public void exportaClientes() {
		
	}
}
