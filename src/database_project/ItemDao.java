package database_project;

import java.sql.*;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

public class ItemDao extends Dao<Item> {
	static final String INSERT = "INSERT INTO Item(item_id,item_name, type, device_type) VALUES(?, ?, ?, ?)";

	@Override
	public Item getById(long item_id) {
		try {
			getConnection().createStatement().executeQuery("select * from test where item_id = '" + item_id +"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Item> getAll() {
		
		return null;
	}
	
	@Override
	public int insert(Item t) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(INSERT);
			stmt.setInt(1, t.getItem_id());
			stmt.setString(2, t.getItem_name());
			stmt.setString(3, t.getType());
			stmt.setString(4, t.getType_device());
			
			int result = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			
			if (resultSet.next()) {
				t.setItem_id(resultSet.getInt(1));
			}
			
			return result;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public int update(Item t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
