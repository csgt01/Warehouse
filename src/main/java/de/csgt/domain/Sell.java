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
	
	private Date soldAt;

	@NotNull
	private Integer quantity;
	
	@NotNull
	private Double price;
	
	private Double totalCosts = 0.0;
	
	private boolean sold;
	private Double addtionalCosts;

	@NotNull
	@ManyToOne
	@JoinColumn
	private Product product;
	
	@OneToMany(mappedBy = "sell")
	@Cascade(value = {CascadeType.ALL})
	private List<SellMaterial> sellMaterials;
	
	@OneToMany(mappedBy = "sell")
	@Cascade(value = {CascadeType.ALL})
	private List<SellBuy> sellbuys;
	
//	@OneToMany(mappedBy = "sell")
//	@Cascade(value = {CascadeType.ALL})
//	private List<AdditionalItems> additionalItems;
	private Double additionalCosts;
	
	public Sell() {
		super();
	}

	public Sell(Long id, Date soldAt, Integer quantity, Double price, Product product, List<SellMaterial> sellMaterials) {
		super();
		this.id = id;
		this.soldAt = soldAt;
		this.quantity = quantity;
		this.price = price;
		this.product = product;
		this.sellMaterials = sellMaterials;
	}

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

	public Double getTotalCosts() {
		return totalCosts;
	}

	public void setTotalCosts(Double totalCosts) {
		this.totalCosts = totalCosts;
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

	public List<SellBuy> getSellBuys() {
		return sellbuys;
	}

	public void setSellBuys(List<SellBuy> sellbuys) {
		this.sellbuys = sellbuys;
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

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public Double getAddtionalCosts() {
		return addtionalCosts;
	}

	public void setAddtionalCosts(Double addtionalCosts) {
		this.addtionalCosts = addtionalCosts;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", "
				+ "\"soldAt\":\"" + soldAt + "\", "
				+ "\"quantity\":\"" + quantity + "\", "
				+ "\"price\":\"" + price + "\", "
				+ "\"product\":\"" + product + "\", "
				+ "\"sold\":\"" + sold + "\" "
//				+ "\"sellMaterials\":\"" + sellMaterials != null ? sellMaterials.toString() : ""
				+ "\"}";
	}

	public Double getAdditionalCosts() {
		return additionalCosts;
	}

	public void setAdditionalCosts(Double additionalCosts) {
		this.additionalCosts = additionalCosts;
	}

	
}
