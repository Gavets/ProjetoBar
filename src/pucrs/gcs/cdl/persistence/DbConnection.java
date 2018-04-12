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

public class DbConnection {
	private static final Path DB_DIR = Paths.get(System.getProperty("user.dir"), "db");
	private static final String DB_URL = "jdbc:h2:" + Paths.get(DB_DIR.toString(), "bar").toUri().toString();
	private static final String DB_USER = "sa";
	private static final String DB_PASS = "";
	private static final String DB_TABLE = "CLIENTE";
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
		ResultSet rs = meta.getTables(null, null, DB_TABLE, null);
		return rs.next();
	}
	
	private static boolean createTable() throws SQLException, IOException {
		
		String sql = new String(Files.readAllBytes(DB_SQL), StandardCharsets.UTF_8);		
		PreparedStatement stmt = conn.prepareStatement(sql);
		return stmt.execute();
	}
	
	public static List<Cliente> getClientes(String filter) throws SQLException, IOException {
		open();
		String sql = "SELECT nome, idade, cpf, genero, socio, numSocio FROM " + DB_TABLE;
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
	
	public static List<Cliente> getClientes() throws SQLException, IOException {
		return getClientes(null);
	}
	
	public static boolean putCliente(Cliente cliente) throws SQLException, IOException {
		boolean result = false;
		open();
		String sql = String.format("INSERT INTO %s(nome, idade, cpf, genero, socio, numSocio) VALUES('%s', %d, '%s', %b, %b, '%s')",
				DB_TABLE, cliente.getNome(), cliente.getIdade(), cliente.getCpf(), cliente.getGenero(), cliente.isSocio(), cliente.getNumSocio());
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		result = stmt.execute();
		close();
		return result;
	}
	
	public static int countClientes(String filter) throws SQLException, IOException {
		open();
		int count = 0;
		String sql = "SELECT COUNT(*) FROM " + DB_TABLE;
		if(filter != null)
			sql += " WHERE " + filter;
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			count = rs.getInt(1);
		
		close();
		return count;
	}
	
	public static int countClientes() throws SQLException, IOException {
		return countClientes(null);
	}
	
	public static boolean isCaixaAberto() throws SQLException, IOException {
		open();
		int count = 0;
		String sql = "SELECT COUNT(*) FROM CAIXA WHERE aberto = TRUE";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			count = rs.getInt(1);
		
		close();
		return count > 0;
	}
	
	public static void abreCaixa() throws SQLException, IOException, CaixaJaAbertoException {
		if(isCaixaAberto()) {
			throw new CaixaJaAbertoException();
		}
		open();
		// TODO abrir caixa
		close();
	}
	
	public static void fechaCaixa() {
		// TODO fechar caixa
	}
	
	public static boolean isClienteNoBar() {
		// TODO Verificar se o cliente está no bar.
		// Tabela RegistroEntradaSaida, procurar por entrou = true
		return false;
	}
	
	public static void clienteEntra(Cliente cliente) {
		// TODO Cliente entra no bar
		// Verifica se cliente já está no bar
		// Insere uma tupla em RegistroEntradaSaida com o horário de entrada
	}
	
	public static void clienteSai(Cliente cliente) {
		// TODO Cliente sai do bar
		// Verifica se cliente está no bar
		// Registra o horário de saída em RegistroEntradaSaida e ajusta "entrou" para false
	}
}
