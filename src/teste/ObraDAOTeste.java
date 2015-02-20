package teste;

import java.sql.SQLException;
import java.util.List;

import modelo.Editora;
import modelo.Obra;
import modelo.ObraTipo;
import DAO.ObraDAO;

public class ObraDAOTeste {
	
	public static void main(String[] args) throws SQLException {
				
		findTeste(30);
//		findAllTeste();
//		insertTeste();

	}
	
	static void findTeste(Integer id) throws SQLException
	{
		ObraDAO obraDao = new ObraDAO();
		Obra obra = obraDao.find(id);
		System.out.println(obra.getTipo().getNome());
	}
	
	static void findAllTeste() throws SQLException
	{
		ObraDAO obraDao = new ObraDAO();
		List<Obra> obras = obraDao.findAll();
		
		for (Obra obra : obras) {
			System.out.println(obra.getNome());
		}
	}
	
	static void insertTeste() throws SQLException
	{
		ObraDAO obraDao = new ObraDAO();
		Editora editora = new Editora();
		editora.setId(7);
		
		ObraTipo tipo = new ObraTipo();
		tipo.setId(1);
		
		Obra obra = new Obra();
		obra.setAno("1982");
		obra.setCodigo("1235");
		obra.setEditora(editora);
		obra.setNome("Livro 23");
		obra.setTipo(tipo);
		
		obraDao.insert(obra);
		
	}

}
