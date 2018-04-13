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
import java.time.LocalDateTime;
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
		return queryInsert(new String(Files.readAllBytes(DB_SQL), StandardCharsets.UTF_8));
	}
	
	public static List<Cliente> getClientes(String filter) throws SQLException, IOException {
		open();
		String sql = "SELECT nome, idade, cpf, genero, socio, numSocio FROM " + DB_TABLE;
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
	
	public static boolean putCliente(Cliente cliente) throws SQLException, IOException {
		open();
		String sql = String.format(
				"INSERT INTO %s(nome, idade, cpf, genero, socio, numSocio) VALUES('%s', %d, '%s', %b, %b, '%s')",
				DB_TABLE, cliente.getNome(), cliente.getIdade(), cliente.getCpf(), cliente.getGenero(), cliente.isSocio(), cliente.getNumSocio()
				);
		
		boolean result = queryInsert(sql);
		close();
		return result;
	}
	
	public static int countClientes(String filter) throws SQLException, IOException {
		open();
		int count = 0;
		String sql = "SELECT COUNT(*) FROM " + DB_TABLE;
		if(filter != null)
			sql += " WHERE " + filter;
		
		ResultSet rs = querySelect(sql);
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
		
		ResultSet rs = querySelect(sql);
		while(rs.next())
			count = rs.getInt(1);
		
		close();
		return count > 0;
	}
	
	public static boolean abreCaixa() throws SQLException, IOException, CaixaJaAbertoException {
		if(isCaixaAberto())
			throw new CaixaJaAbertoException();
		
		open();
		String sql = "INSERT INTO CAIXA(dia, aberto) VALUES(" + LocalDateTime.now().toString() + ", true)";
		boolean result = queryInsert(sql);
		close();
		return result;
	}
	
	public static boolean fechaCaixa() throws SQLException, IOException, SemCaixaAbertoException {
		if(!isCaixaAberto())
			throw new SemCaixaAbertoException();
		
		open();
		String sql = "UPDATE CAIXA SET aberto = FALSE WHERE aberto = TRUE";
		int result = queryUpdate(sql);
		close();
		return result > 0;
	}
	
	public static boolean isClienteNoBar(Cliente c) throws SQLException {
		String sql = "SELECT COUNT(*) FROM RegistroEntradaSaida WHERE Entrou = TRUE AND CPF = '" + c.getCpf();
		ResultSet rs = querySelect(sql);
		int count = 0;
		while(rs.next())
			count = rs.getInt(1);
		
		return count > 0;
	}
	
	public static boolean clienteEntra(Cliente cliente) throws SQLException, ClienteJaNoBarException, SemCaixaAbertoException, IOException {
		if(!isCaixaAberto())
			throw new SemCaixaAbertoException();
		
		if(isClienteNoBar(cliente))
			throw new ClienteJaNoBarException();
		
		int idCaixa = getIdCaixaAberto();
		open();
		String sql = String.format("INSERT INTO Cliente_Caixa(id_Caixa, cpf) VALUES(%d, '%s')", cliente.getCpf(), idCaixa);
		if(!queryInsert(sql))
			throw new SQLException("Falha ao inserir cliente");
		close();
	
		int idClienteCaixa = getIdClienteCaixa(cliente);
		open();
		sql = String.format("INSERT INTO RegistroEntradaSaida(id_cliente_caixa, entrou, horario_entrada) VALUES(%d, %b, %s)", idClienteCaixa, true, LocalDateTime.now().toString());
		boolean result = queryInsert(sql);
		close();
		return result;
	}
	
	public static boolean clienteSai(Cliente cliente) throws SemCaixaAbertoException, SemClienteNoBarException, SQLException, IOException {
		if(!isCaixaAberto())
			throw new SemCaixaAbertoException();
		
		if(!isClienteNoBar(cliente))
			throw new SemClienteNoBarException();
		
		int idCaixa = getIdCaixaAberto();
		open();
		String sql = String.format("INSERT INTO Cliente_Caixa(id_Caixa, cpf) VALUES(%d, '%s')", cliente.getCpf(), idCaixa);
		if(!queryInsert(sql))
			throw new SQLException("Falha ao inserir cliente");
		close();
	
		int idClienteCaixa = getIdClienteCaixa(cliente);
		open();
		sql = String.format("UPDATE RegistroEntradaSaida SET horario_saida = '%s', entrou = FALSE WHERE id = %d", LocalDateTime.now(), idClienteCaixa);
		boolean result = queryInsert(sql);
		close();
		return result;
	}
	
	private static int getIdCaixaAberto() throws SQLException, SemCaixaAbertoException, IOException {
		if(!isCaixaAberto())
			throw new SemCaixaAbertoException();
		
		open();
		String sql = "SELECT id_caixa FROM CAIXA WHERE aberto = TRUE";
		ResultSet rs = querySelect(sql);
		int id = -1;
		while(rs.next())
			id = rs.getInt(1);
		close();
		return id;
	}
	
	private static int getIdClienteCaixa(Cliente cliente) throws SQLException, SemCaixaAbertoException, IOException {
		if(!isCaixaAberto())
			throw new SemCaixaAbertoException();
		
		open();
		String sql = "SELECT id_cliente_caixa FROM Cliente_Caixa CC WHERE CC.CPF = '" + cliente.getCpf() + "' "
					+ "INNER JOIN CAIXA C WHERE C.aberto = true"; 
		ResultSet rs = querySelect(sql);
		int id = -1;
		while(rs.next())
			id = rs.getInt(1);
		close();
		return id;
	}
	
	private static ResultSet querySelect(String sql) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		return stmt.executeQuery();
	}
	
	private static boolean queryInsert(String sql) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		return stmt.execute();
	}
	
	private static int queryUpdate(String sql) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		return stmt.executeUpdate();
	}
}
