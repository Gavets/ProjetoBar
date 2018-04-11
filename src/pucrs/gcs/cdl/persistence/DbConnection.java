package pucrs.gcs.cdl.persistence;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pucrs.gcs.cdl.business.Cliente;

public class DbConnection {
	private static final String DB_URL = "jdbc:h2:" + Paths.get(System.getProperty("user.dir"), "db", "bar").toString();
	private static final String DB_USER = "";
	private static final String DB_PASS = "";
	private static Connection conn;
	
	private static void open() throws SQLException {
		if(conn == null || conn.isClosed())
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
	
	private static void close() throws SQLException {
		if(conn != null && !conn.isClosed())
			conn.close();
	}
	
	private static void createDb() throws SQLException {
		open();	
		
		close();
	}
	
	public static List<Cliente> getClientes(String filter) throws SQLException {
		open();
		String sql = "SELECT nome, idade, cpf, genero, socio, numSocio FROM CLIENTES";
		if(filter != null)
			sql += " WHERE " + filter;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<Cliente> clientes = new ArrayList<>();
		while(rs.next())
			clientes.add(new Cliente(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5), rs.getString(6)));
		
		close();
		return clientes;
	}
}
