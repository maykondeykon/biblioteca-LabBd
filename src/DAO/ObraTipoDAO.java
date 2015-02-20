package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.ObraTipo;
import biblioteca.ConnectionPool;

public class ObraTipoDAO 
{

private Connection conn;
	
	private void getConnection() throws SQLException
	{
		ConnectionPool database = new ConnectionPool();
		Connection connection = database.getConnection();
		this.conn = connection;
	}
	
	public ObraTipo find(Integer id) throws SQLException
	{
		getConnection();
		
		String sql = "Select * from obraTipo where id = ?";
		PreparedStatement stmt;
		try{
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			ObraTipo obraTipo = new ObraTipo();
			while(rs.next()){
				obraTipo.setId(rs.getInt("id"));
				obraTipo.setNome(rs.getString("nome"));
			}
			rs.close();
			return obraTipo;
			
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
	
	public List<ObraTipo> findAll() throws SQLException
	{
		getConnection();
		
		String sql = "Select * from obraTipo";
		PreparedStatement stmt;
		
		try {
			stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<ObraTipo> tipos = new ArrayList<ObraTipo>();
			
			while(rs.next()){
				ObraTipo obraTipo = new ObraTipo();
				obraTipo.setId(rs.getInt("id"));
				obraTipo.setNome(rs.getString("nome"));
				
				tipos.add(obraTipo);
			}
			
			rs.close();
			return tipos;
			
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
	
	public void insert(ObraTipo obraTipo) throws SQLException
	{
		getConnection();
		
		String sql = "INSERT INTO obraTipo(nome) VALUES(?)";
		
		try(PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
		{
			stmt.setString(1,obraTipo.getNome());
			stmt.execute();
			
			try(ResultSet rs = stmt.getGeneratedKeys()){
				if(rs.next()){
					int id = rs.getInt(1);
					obraTipo.setId(id);
				}
			}
		}
		
	}
}
