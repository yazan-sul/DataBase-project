package database_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

public class ProducerDao extends Dao<Producer> {
	static final String INSERT = "INSERT INTO producer(producer_id,producer_name) VALUES(?, ?)";
	static final String DELETE = "DELETE from producer where producer_id =?;";
	static final String UPDATE = "UPDATE producer SET producer_id=?, producer_name=? where producer_id=?";
	
	
	@Override
	public Producer getById(long Producer_id) {
		try {
			ResultSet resutl = getConnection().createStatement()
					.executeQuery("select * from Producer where Producer_id  = '" + Producer_id + "';");
			return new Producer(resutl.getInt(1), resutl.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Producer> getAll() {
		List<Producer> list = new ArrayList<Producer>();
		try {
			ResultSet resutl = getConnection().createStatement().executeQuery("select * from Producer;");
			while (resutl.next())
				list.add(new Producer(resutl.getInt(1), resutl.getString(2)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(Producer t) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			int[] emptyArr = new int[0];
			stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, t.getProducer_id());
			stmt.setString(2, t.getProducer_name());

			int result = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();

			if (resultSet.next()) {
				t.setProducer_id(resultSet.getInt(1));
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
	public int update(Producer producer) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, producer.getProducer_id());
			stmt.setString(2, producer.getProducer_name());
			stmt.setInt(3, producer.getProducer_id());

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
