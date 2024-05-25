package database_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorageDao extends Dao<Storage> {
	static final String INSERT = "INSERT INTO Storages(storage_id,storage_name,shelf_num) VALUES(?, ?, ?)";
	static final String DELETE = "DELETE from Storages where storage_id =?;";
	static final String UPDATE = "UPDATE Storages SET storage_id=?, storage_name=?, shelf_num=?";

	@Override
	public Storage getById(long storage_id) {
		try {
			ResultSet resutl = getConnection().createStatement()
					.executeQuery("select * from Storages where storage_id  = '" + storage_id + "';");
			return new Storage(resutl.getInt(1), resutl.getString(2), resutl.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Storage> getAll() {
		List<Storage> list = new ArrayList<Storage>();
		try {
			ResultSet resutl = getConnection().createStatement().executeQuery("select * from Storages;");
			while (resutl.next())
				list.add(new Storage(resutl.getInt(1), resutl.getString(2), resutl.getString(3)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insert(Storage t) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			int[] emptyArr = new int[0];
			stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, t.getStorage_id());
			stmt.setString(2, t.getStorage_name());
			stmt.setString(3, t.getShelf_num());

			int result = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();

			if (resultSet.next()) {
				t.setStorage_id(resultSet.getInt(1));
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
	public int update(Storage storage) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, storage.getStorage_id());
			stmt.setString(2, storage.getStorage_name());
			stmt.setString(3, storage.getShelf_num());

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
