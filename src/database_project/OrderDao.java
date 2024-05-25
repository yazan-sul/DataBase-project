package database_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

public class OrderDao extends Dao<Order> {
	static final String INSERT = "INSERT INTO order(order_id,request_date,receipt_date,client_id) VALUES(?, ?, ?, ?)";
	static final String DELETE = "DELETE from order where order_id =?;";
	static final String UPDATE = "UPDATE order SET order_id=?, request_date=?, receipt_date=?, client_id=?";
	
	
	@Override
	public Order getById(long order_id) {
		try {
			ResultSet resutl = getConnection().createStatement()
					.executeQuery("select * from order where order_id  = '" + order_id + "';");
			return new Order(resutl.getInt(1), null,null,0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> getAll() {
		List<Order> list = new ArrayList<Order>();
		try {
			ResultSet resutl = getConnection().createStatement().executeQuery("select * from order;");
			while (resutl.next())
				list.add( new Order(resutl.getInt(1), null,null,0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(Order t) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			int[] emptyArr = new int[0];
			stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, t.getOrder_id());
			stmt.setDate(2, t.getReceipt_date());
			stmt.setDate(3, t.getRequest_date());
			stmt.setInt(4, t.getClient_id());
			int result = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();

			if (resultSet.next()) {
				t.setOrder_id(resultSet.getInt(1));
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
	public int update(Order order) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, order.getOrder_id());
			stmt.setDate(2, order.getReceipt_date());
			
			return stmt.executeUpdate();
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
	public int delete(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, id);

			return stmt.executeUpdate();
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
}
