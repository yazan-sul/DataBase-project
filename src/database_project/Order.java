package database_project;

import java.sql.Date;

public class Order {
	private int order_id;
	private Date request_date;
	private Date receipt_date;
	private int client_id;
	public Order(int order_id, Date request_date, Date receipt_date, int client_id) {
		super();
		this.order_id = order_id;
		this.request_date = request_date;
		this.receipt_date = receipt_date;
		this.client_id = client_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Date getRequest_date() {
		return request_date;
	}
	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}
	public Date getReceipt_date() {
		return receipt_date;
	}
	public void setReceipt_date(Date receipt_date) {
		this.receipt_date = receipt_date;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	
}
