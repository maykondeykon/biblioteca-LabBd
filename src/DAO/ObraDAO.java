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
import modelo.Obra;
import modelo.ObraTipo;

public class ObraDAO {
	
	private Connection conn;
	
	private void getConnection() throws SQLException
	{
		ConnectionPool database = new ConnectionPool();
		Connection connection = database.getConnection();
		this.conn = connection;
	}

	public void insert(Obra obra) throws SQLException
	{
		getConnection();
		
		String sql = "INSERT INTO obra(codigo, nome, ano, editora, tipo) VALUES(?, ?, ?, ?, ?)";
		
		try(PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
		{
			stmt.setString(1,obra.getCodigo());
			stmt.setString(2,obra.getNome());
			stmt.setString(3,obra.getAno());
			stmt.setInt(4,obra.getEditora().getId());
			stmt.setInt(5,obra.getTipo().getId());
			stmt.execute();
			
			try(ResultSet rs = stmt.getGeneratedKeys()){
				if(rs.next()){
					int id = rs.getInt(1);
					obra.setId(id);
				}
			}
		}
	}
	
	public Obra find(Integer id) throws SQLException
	{
		getConnection();
		
		EditoraDAO editoraDao = new EditoraDAO();
		ObraTipoDAO obraTipoDao = new ObraTipoDAO(); 
		
		String sql = "Select * from obra where obra.id = ?";
		PreparedStatement stmt;
		try{
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			Obra obra = new Obra();
			while(rs.next()){
				obra.setId(rs.getInt("obra.id"));
				obra.setCodigo(rs.getString("obra.codigo"));
				obra.setNome(rs.getString("obra.nome"));
				obra.setAno(rs.getString("obra.ano"));
				
				Editora editora = editoraDao.find(rs.getInt("obra.editora"));
				
				ObraTipo tipo = obraTipoDao.find(rs.getInt("obra.tipo"));
				
				obra.setEditora(editora);
				obra.setTipo(tipo);
			}
			rs.close();
			return obra;
			
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
	
	public List<Obra> findAll() throws SQLException
	{
		getConnection();
		
		EditoraDAO editoraDao = new EditoraDAO();
		ObraTipoDAO obraTipoDao = new ObraTipoDAO(); 
		
		String sql = "Select * from obra order by obra.id";
		PreparedStatement stmt;
		try {
			stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Obra> obras = new ArrayList<Obra>();
			
			while(rs.next()){
				Obra obra = new Obra();
				obra.setId(rs.getInt("obra.id"));
				obra.setCodigo(rs.getString("obra.codigo"));
				obra.setNome(rs.getString("obra.nome"));
				obra.setAno(rs.getString("obra.ano"));
				
				Editora editora = editoraDao.find(rs.getInt("obra.editora"));
				
				ObraTipo tipo = obraTipoDao.find(rs.getInt("obra.tipo"));
				
				obra.setEditora(editora);
				obra.setTipo(tipo);
				obras.add(obra);
			}
			
			rs.close();
			return obras;
			
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
