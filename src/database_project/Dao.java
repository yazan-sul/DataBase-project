package database_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public abstract class Dao<T> {
	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/pro";
	private static final String ID = "root";
	private static final String PASS = "mzu9q0j2";

	Connection getConnection() {
		try {
			Class.forName(DRIVER_NAME);
			return DriverManager.getConnection(DB_URL, ID, PASS);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

    public abstract T getById(long id);
    
    public abstract List<T> getAll();
    
    public abstract int insert(T t);
    
    public abstract int update(T t);
    
    public abstract int delete(int id);
}
