package model;

import java.sql.Date;

import javax.faces.bean.ManagedBean;

import org.hibernate.annotations.Entity;
import org.springframework.stereotype.Component;


@Entity
@ManagedBean(name="productBean")
public class Products {

	
	private int stock_id;
	private int category_id;
	private String product_name;
	private String size;
	private float purchase;
	private float sale;
	private Date sDate;

	public Products() {
		// TODO Auto-generated constructor stub
	}
	
	public Products(int stock_id, int category_id, String product_name, String size, float purchase, float sale,
			Date sDate) {
		super();
		this.stock_id = stock_id;
		this.category_id = category_id;
		this.product_name = product_name;
		this.size = size;
		this.purchase = purchase;
		this.sale = sale;
		this.sDate = sDate;
	}

	
	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public float getPurchase() {
		return purchase;
	}

	public void setPurchase(float purchase) {
		this.purchase = purchase;
	}

	public float getSale() {
		return sale;
	}

	public void setSale(float sale) {
		this.sale = sale;
	}

	public Date getsDate() {
		return sDate;
	}

	

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	
	

	
	

	
}
