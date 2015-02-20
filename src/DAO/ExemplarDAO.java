package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biblioteca.ConnectionPool;
import modelo.Exemplar;
import modelo.Obra;

public class ExemplarDAO {
	
	private Connection conn;
	
	private void getConnection() throws SQLException
	{
		ConnectionPool database = new ConnectionPool();
		Connection connection = database.getConnection();
		this.conn = connection;
	}

	public void insert(Exemplar exemplar) throws SQLException
	{
		getConnection();
		
		String sql = "insert into exemplar(obra,dtaAquisicao) values(?,?)";
		
		try(PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
		{
			stmt.setInt(1,exemplar.getObra().getId());
			stmt.setDate(2,new java.sql.Date(exemplar.getDtaAquisicao().getTime()));
			stmt.execute();
			
			try(ResultSet rs = stmt.getGeneratedKeys()){
				if(rs.next()){
					int id = rs.getInt(1);
					exemplar.setId(id);
				}
			}
		}
	}
	
	public Exemplar find(Integer id) throws SQLException
	{
		getConnection();
		
		ObraDAO obraDao = new ObraDAO();
		
		String sql = "Select * from exemplar where id = ?";
		PreparedStatement stmt;
		try{
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			Exemplar exemplar = new Exemplar();
			while(rs.next()){
				exemplar.setId(rs.getInt("id"));
				exemplar.setDtaAquisicao(rs.getDate("dtaAquisicao"));
				
				Obra obra = obraDao.find(rs.getInt("obra"));
				
				exemplar.setObra(obra);
			}
			rs.close();
			return exemplar;
			
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
	
	public List<Exemplar> findAll() throws SQLException
	{
		getConnection();
		
		ObraDAO obraDao = new ObraDAO();
		
		String sql = "Select * from exemplar join obra on obra.id = exemplar.obra join editora on obra.editora = editora.id join obraTipo on obra.tipo = obraTipo.id order by exemplar.id";
		PreparedStatement stmt;
		try {
			stmt = this.conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			List<Exemplar> exemplares = new ArrayList<Exemplar>();
			
			while(rs.next()){
				Exemplar exemplar = new Exemplar();
				exemplar.setId(rs.getInt("exemplar.id"));
				exemplar.setDtaAquisicao(rs.getDate("exemplar.dtaAquisicao"));
				
				Obra obra = obraDao.find(rs.getInt("exemplar.obra"));
				exemplar.setObra(obra);
				
				exemplares.add(exemplar);
			}
			
			return exemplares;
			
		} catch (SQLException e) {
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
}
