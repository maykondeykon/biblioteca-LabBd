package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import modelo.Editora;
import biblioteca.ConnectionPool;

public class AbstractDAO {
	private static Connection conn;
	private static EditoraDAO dao;

	public static void main(String[] args) throws SQLException {

		ConnectionPool database = new ConnectionPool();
		Connection connection = database.getConnection();
		conn = connection;
		
		dao = new EditoraDAO(conn);
		
//		insertTeste();
//		findAllTeste();
//		findTeste(11);
		
		Editora editora = dao.find(11);
		Field[] fields = Editora.class.getDeclaredFields();
		
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		
		Method[] fields1 = Editora.class.getDeclaredMethods();
		
		for (Method field : fields1) {
			System.out.println(field.getName());
		}

	}
}
