package database_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

public class ClientDao extends Dao<Client> {
	static final String INSERT = "INSERT INTO Client(client_id,client_name) VALUES(?, ?)";
	static final String DELETE = "DELETE from Client where client_id =?;";
	static final String UPDATE = "UPDATE Client SET client_id=?, client_name=? where client_id=?";
	
	
	@Override
	public Client getById(long client_id) {
		try {
			ResultSet resutl = getConnection().createStatement()
					.executeQuery("select * from Client where client_id  = '" + client_id + "';");
			return new Client(resutl.getInt(1), resutl.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Client> getAll() {
		List<Client> list = new ArrayList<Client>();
		try {
			ResultSet resutl = getConnection().createStatement().executeQuery("select * from Client;");
			while (resutl.next())
				list.add(new Client(resutl.getInt(1), resutl.getString(2)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(Client t) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			int[] emptyArr = new int[0];
			stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, t.getClient_id());
			stmt.setString(2, t.getClient_name());

			int result = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();

			if (resultSet.next()) {
				t.setClient_id(resultSet.getInt(1));
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
	public int update(Client client) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, client.getClient_id());
			stmt.setString(2, client.getClient_name());
			stmt.setInt(3, client.getClient_id());
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
