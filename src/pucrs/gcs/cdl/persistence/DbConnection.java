package pucrs.gcs.cdl.persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pucrs.gcs.cdl.business.Cliente;
import pucrs.gcs.cdl.exception.ClienteJaNoBarException;
import pucrs.gcs.cdl.exception.ClienteForaDoBarException;

public class DbConnection {
	private static final Path DB_DIR = Paths.get(System.getProperty("user.dir"), "db");
	private static final String DB_URL = "jdbc:h2:" + Paths.get(DB_DIR.toString(), "bar").toUri().toString();
	private static final String DB_USER = "sa";
	private static final String DB_PASS = "";
	private static final Path DB_SQL = Paths.get(DB_DIR.toString(), "Bar_ER.sql");
	private static Connection conn;
	
	private static void open() throws SQLException, IOException {
		if(Files.notExists(DB_DIR, new LinkOption[] {LinkOption.NOFOLLOW_LINKS}))
			Files.createDirectory(DB_DIR);
		
		if(conn == null || conn.isClosed())
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		
		if(!tableExists())
			createTable();
	}
	
	private static void close() throws SQLException {
		if(conn != null && !conn.isClosed())
			conn.close();
	}
	
	private static boolean tableExists() throws SQLException {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getTables(null, null, "CLIENTE", null);
		return rs.next();
	}
	
	private static boolean createTable() throws SQLException, IOException {
		return queryInsert(new String(Files.readAllBytes(DB_SQL), StandardCharsets.UTF_8));
	}
	
	public static List<Cliente> getClientes(String filter) throws SQLException, IOException {
		open();
		String sql = "SELECT nome, idade, cpf, genero, socio, numSocio FROM Cliente";
		if(filter != null)
			sql += " WHERE " + filter;
		ResultSet rs = querySelect(sql);
		
		List<Cliente> clientes = new ArrayList<>();
		while(rs.next())
			clientes.add(new Cliente(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5), rs.getString(6)));
		
		close();
		return clientes;
	}
	
	public static List<Cliente> getClientes() throws SQLException, IOException {
		return getClientes(null);
	}
	
	public static List<Cliente> getClientesNoBar(String filter) throws SQLException, IOException {
		open();				
		String sql =  "SELECT C.nome, C.idade, C.cpf, C.genero, C.socio, C.numSocio FROM Cliente C"
					+ " INNER JOIN Bar B ON B.cliente_cpf = C.cpf";
		if(filter != null)
			sql += " WHERE " + filter;

		ResultSet rs = querySelect(sql);
		
		List<Cliente> clientes = new ArrayList<>();
		while(rs.next())
			clientes.add(new Cliente(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5), rs.getString(6)));
		
		close();
		return clientes;
	}
	
	public static List<Cliente> getClientesNoBar() throws SQLException, IOException {
		return getClientesNoBar(null);
	}
	
	public static boolean putCliente(Cliente cliente) throws SQLException, IOException {
		String sql = String.format(
				"INSERT INTO Cliente(nome, idade, cpf, genero, socio, numSocio) VALUES('%s', %d, '%s', %b, %b, '%s')",
				cliente.getNome(), cliente.getIdade(), cliente.getCpf(), cliente.getGenero(), cliente.isSocio(), cliente.getNumSocio()
				);
		
		return queryInsert(sql);
	}
	
	public static int countClientes(String filter) throws SQLException, IOException {
		String sql = "SELECT COUNT(*) FROM Cliente";
		if(filter != null)
			sql += " WHERE " + filter;
		
		return queryCount(sql);
	}
	
	public static int countClientes() throws SQLException, IOException {
		return countClientes(null);
	}
	
	public static int countClientesNoBar(String filter) throws SQLException, IOException {
		String sql = "SELECT COUNT(*) FROM Bar";
		if(filter != null)
			sql += " INNER JOIN Cliente C ON C.cpf = cliente_cpf AND " + filter;
		
		return queryCount(sql);
	}
	
	public static int countClientesNoBar() throws SQLException, IOException {
		return countClientesNoBar(null);
	}
	
	public static int countMulheresNoBar() throws SQLException, IOException {
		return countClientesNoBar("C.genero = TRUE");
	}
	
	public static int countSociosNoBar() throws SQLException, IOException {
		return countClientesNoBar("C.socio = TRUE");
	}
	
	public static boolean isClienteNoBar(String cpf) throws SQLException, IOException {
		String sql = String.format("SELECT COUNT(*) FROM Bar WHERE cliente_cpf = '%s'", cpf);
		return queryCount(sql) > 0;
	}
	
	public static boolean isClienteNoBar(Cliente cliente) throws SQLException, IOException {
		return isClienteNoBar(cliente.getCpf());
	}
	
	public static boolean clienteExists(Cliente cliente ) throws SQLException, IOException {
		String sql = String.format("SELECT COUNT(*) FROM Cliente WHERE cpf = '%s'", cliente.getCpf());
		return queryCount(sql) > 0;
	}
	
	public static boolean clienteEntra(Cliente cliente) throws SQLException, ClienteJaNoBarException, IOException {		
		if(isClienteNoBar(cliente))
			throw new ClienteJaNoBarException();

		String sql = String.format("INSERT INTO Bar(cliente_cpf) VALUES('%s')", cliente.getCpf());
		return queryInsert(sql);
	}
	
	public static boolean clienteSai(Cliente cliente) throws ClienteForaDoBarException, SQLException, IOException {		
		if(!isClienteNoBar(cliente))
			throw new ClienteForaDoBarException();

		String sql = String.format("DELETE FROM Bar WHERE cliente_cpf = '%s'", cliente.getCpf());
		return queryInsert(sql);
	}
	
	private static ResultSet querySelect(String sql) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		return stmt.executeQuery();
	}
	
	private static boolean queryInsert(String sql) throws SQLException, IOException {
		open();
		PreparedStatement stmt = conn.prepareStatement(sql);
		boolean result = stmt.execute();
		close();
		return result;
	}
	
	private static int queryCount(String sql) throws SQLException, IOException {
		int count = 0;
		open();
		ResultSet rs = querySelect(sql);
		while(rs.next())
			count = rs.getInt(1);
		close();
		return count;
	}
	
	/*
	private static int queryUpdate(String sql) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		return stmt.executeUpdate();
	}
	*/
}
