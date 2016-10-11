package de.csgt.domain;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Sell {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Calendar createdAt;
	
	@NotNull
	private Date soldAt;

	@NotNull
	private Integer quantity;
	
	@NotNull
	private Double price;

	@NotNull
	@ManyToOne
	@JoinColumn
	private Product product;
	
	@OneToMany
	@Cascade(value = {CascadeType.ALL})
	private List<SellMaterial> sellMaterials;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public List<SellMaterial> getSellMaterials() {
		return sellMaterials;
	}

	public void setSellMaterials(List<SellMaterial> sellMaterials) {
		this.sellMaterials = sellMaterials;
	}

	public Date getSoldAt() {
		return soldAt;
	}

	public void setSoldAt(Date soldAt) {
		this.soldAt = soldAt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", "
				+ "\"createdAt\":\"" + createdAt + "\", "
				+ "\"soldAt\":\"" + soldAt + "\", "
				+ "\"quantity\":\"" + quantity + "\", "
				+ "\"price\":\"" + price + "\", "
				+ "\"product\":\"" + product + "\", "
//				+ "\"sellMaterials\":\"" + sellMaterials != null ? sellMaterials.toString() : ""
				+ "\"}";
	}

	
}
