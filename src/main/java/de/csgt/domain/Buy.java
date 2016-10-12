package de.csgt.domain;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Buy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Calendar createdAt;
	
	@NotNull
	private Date broughtAt;

	@NotNull
	private Integer quantity;
	
	@Transient
	private Integer tempQuantity = 0;
	
	@NotNull
	private Double price;
	
	private boolean sold = true;
	private int soldInt = 0;

	@NotNull
	@ManyToOne
	@JoinColumn
	private Material material;

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

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	public Date getBroughtAt() {
		return broughtAt;
	}
	
	public void setBroughtAt(Date broughtAt) {
		this.broughtAt = broughtAt;
	}


	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getTempQuantity() {
		return tempQuantity;
	}

	public void setTempQuantity(Integer tempQuantity) {
		this.tempQuantity = tempQuantity;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean allSold) {
		this.sold = allSold;
	}

	public Integer getSoldInt() {
		return soldInt;
	}

	public void setSoldInt(Integer soldInt) {
		this.soldInt = soldInt;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"createdAt\":\"" + createdAt + "\", \"broughtAt\":\"" + broughtAt + "\", \"quantity\":\"" + quantity + "\", \"tempQuantity\":\"" + tempQuantity + "\", \"price\":\"" + price + "\", \"sold\":\"" + sold
				+ "\", \"soldInt\":\"" + soldInt + "\", \"material\":\"" + material + "\"}";
	}
	
}
