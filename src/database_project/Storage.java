package database_project;

public class Storage {

	private int storage_id;
	private String storage_name;
	private String shelf_num;

	public Storage(int storage_id, String storage_name, String shelf_num) {
		super();
		this.storage_id = storage_id;
		this.storage_name = storage_name;
		this.shelf_num = shelf_num;
	}

	public int getStorage_id() {
		return storage_id;
	}

	public void setStorage_id(int storage_id) {
		this.storage_id = storage_id;
	}

	public String getStorage_name() {
		return storage_name;
	}

	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name;
	}

	public String getShelf_num() {
		return shelf_num;
	}

	public void setShelf_num(String shelf_num) {
		this.shelf_num = shelf_num;
	}

}
