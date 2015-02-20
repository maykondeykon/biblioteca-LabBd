package teste;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.Exemplar;
import modelo.Obra;
import DAO.ExemplarDAO;
import DAO.ObraDAO;

public class ExemplarDAOTeste {
	
	private static ExemplarDAO dao;
	
	public static void main(String[] args) throws SQLException {
		
		dao = new ExemplarDAO();

//		findAllTeste();
//		insertTeste();
		findTeste(16);

	}
	
	static void findTeste(Integer id) throws SQLException
	{
		Exemplar exemplar = dao.find(id);
		System.out.println(exemplar.getObra().getNome());
	}
	
	static void findAllTeste() throws SQLException
	{
		ExemplarDAO exemplarDao = new ExemplarDAO();
		List<Exemplar> exemplares = exemplarDao.findAll();
		
		for (Exemplar exemplar : exemplares) {
			System.out.println(exemplar.getObra().getNome());
		}
	}
	
	static void insertTeste() throws SQLException
	{
		ExemplarDAO exemplarDao = new ExemplarDAO();
		
		ObraDAO obraDao = new ObraDAO();
		Obra obra = obraDao.find(31);
		
		
		Date data = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 02, 19);
		
		Exemplar exemplar = new Exemplar();
		exemplar.setDtaAquisicao(data);
		exemplar.setObra(obra);
		
		exemplarDao.insert(exemplar);
		System.out.println("Inserido.");
		
	}

}
