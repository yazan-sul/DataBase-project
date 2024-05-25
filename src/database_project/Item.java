package database_project;

public class Item {
	private int item_id;
	private String  item_name;
	private String  type;
	private String  type_device;
	
	public Item(int item_id, String item_name, String type, String type_device) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.type = type;
		this.type_device = type_device;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_device() {
		return type_device;
	}
	public void setType_device(String type_device) {
		this.type_device = type_device;
	}
	
}
