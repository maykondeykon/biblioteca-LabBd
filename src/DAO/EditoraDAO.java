package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biblioteca.ConnectionPool;
import modelo.Editora;

public class EditoraDAO {

	private Connection conn;
	
	private void getConnection() throws SQLException
	{
		ConnectionPool database = new ConnectionPool();
		Connection connection = database.getConnection();
		this.conn = connection;
	}
	
	public void insert(Editora editora) throws SQLException
	{
		getConnection();
		
		String sql = "INSERT INTO editora(nome, endereco) VALUES(?, ?)";
		
		try(PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
		{
			stmt.setString(1,editora.getNome());
			stmt.setString(2,editora.getEndereco());
			stmt.execute();
			
			try(ResultSet rs = stmt.getGeneratedKeys()){
				if(rs.next()){
					int id = rs.getInt(1);
					editora.setId(id);
				}
			}
		}
		
	}
	
	public Editora find(Integer id) throws SQLException
	{
		getConnection();
		
		String sql = "Select * from editora where editora.id = ?";
		PreparedStatement stmt;
		try{
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			Editora editora = new Editora();
			while(rs.next()){
				editora.setId(rs.getInt("editora.id"));
				editora.setNome(rs.getString("editora.nome"));
				editora.setEndereco(rs.getString("editora.endereco"));
			}
			rs.close();
			return editora;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally{
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public List<Editora> findAll() throws SQLException
	{
		getConnection();
		
		String sql = "Select * from editora";
		PreparedStatement stmt;
		
		try {
			stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Editora> editoras = new ArrayList<Editora>();
			
			while(rs.next()){
				Editora editora = new Editora();
				editora.setId(rs.getInt("editora.id"));
				editora.setNome(rs.getString("editora.nome"));
				editora.setEndereco(rs.getString("editora.endereco"));
				
				editoras.add(editora);
			}
			
			rs.close();
			return editoras;
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		finally{
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
