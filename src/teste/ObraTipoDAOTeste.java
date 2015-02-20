package teste;

import java.sql.SQLException;
import java.util.List;

import modelo.ObraTipo;
import DAO.ObraTipoDAO;

public class ObraTipoDAOTeste {
	
	private static ObraTipoDAO dao;

	public static void main(String[] args) throws SQLException {

		dao = new ObraTipoDAO();
		
//		insertTeste();
		findAllTeste();
//		findTeste(2);

	}
	
	static void insertTeste() throws SQLException
	{
		ObraTipo obraTipo = new ObraTipo();
		obraTipo.setNome("Revista");
		
		dao.insert(obraTipo);
		System.out.println("Inserido.");
	}
	
	static void findTeste(Integer id) throws SQLException
	{
		ObraTipo obraTipo = dao.find(id);
		System.out.println(obraTipo.getNome());
	}
	
	static void findAllTeste() throws SQLException
	{
		List<ObraTipo> tipos = dao.findAll();
		
		for (ObraTipo tipo : tipos) {
			System.out.println(tipo.getNome());
		}
	}

}
