package database_project;

public class Producer {
	private int producer_id;
	private String producer_name;

	public Producer(int producer_id, String producer_name) {
		super();
		this.producer_id = producer_id;
		this.producer_name = producer_name;
	}

	public int getProducer_id() {
		return producer_id;
	}

	public void setProducer_id(int producer_id) {
		this.producer_id = producer_id;
	}

	public String getProducer_name() {
		return producer_name;
	}

	public void setProducer_name(String producer_name) {
		this.producer_name = producer_name;
	}

}
