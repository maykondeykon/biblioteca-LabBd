package teste;

import java.sql.SQLException;
import java.util.List;

import modelo.Editora;
import DAO.EditoraDAO;

public class EditoraDAOTeste {
	
	private static EditoraDAO dao;

	public static void main(String[] args) throws SQLException {

		dao = new EditoraDAO();
		
//		insertTeste();
//		findAllTeste();
		findTeste(11);
		
//		Editora editora = dao.find(11);
//		Field[] fields = Editora.class.getDeclaredFields();
//		
//		for (Field field : fields) {
//			System.out.println(field.getName());
//		}
//		
//		Method[] fields1 = Editora.class.getDeclaredMethods();
//		
//		for (Method field : fields1) {
//			System.out.println(field.getName());
//		}

	}
	
	static void insertTeste() throws SQLException
	{
		Editora editora = new Editora();
		editora.setNome("Editora segunda");
		editora.setEndereco("Rua sem nome");
		
		dao.insert(editora);
		System.out.println("Inserido.");
	}
	
	static void findTeste(Integer id) throws SQLException
	{
		Editora editora = dao.find(id);
		System.out.println(editora.getNome());
	}
	
	static void findAllTeste() throws SQLException
	{
		List<Editora> editoras = dao.findAll();
		
		for (Editora editora : editoras) {
			System.out.println(editora.getNome());
		}
	}

}
