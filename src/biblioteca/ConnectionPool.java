package biblioteca;

import java.sql.SQLException;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionPool 
{

	private MysqlDataSource dataSource; 
	
	public ConnectionPool()
	{
		MysqlDataSource pool= new MysqlDataSource();
		pool.setUrl("jdbc:mysql://localhost/biblioteca");
		pool.setUser("root");
		pool.setPassword("mysql07");
		
		this.dataSource = (MysqlDataSource) pool;
	}
	
	public Connection getConnection() throws SQLException
	{
		Connection connection = (Connection) ((MysqlDataSource) dataSource).getConnection();
		return connection;
	}
}
