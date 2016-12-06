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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Buy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Calendar createdAt;
	
	private Date broughtAt;

	@NotNull
	private Integer quantity;
	
	@NotNull
	private Double price;
	
	private Double totalPrice;
	
	private boolean sold = false;
	private int soldInt = 0;
	private Integer tempQuantity = 0;
	
	@NotNull
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Assignment assignment;

	@OneToMany(mappedBy = "buy")
	@Cascade(value = {CascadeType.ALL})
	private List<SellBuy> sellbuys;

	@NotNull
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Material material;
	
	public Buy() {
		super();
	}

	public Buy(Long id, Date broughtAt, Integer quantity, Integer tempQuantity, Double price, boolean sold, int soldInt, Material material, Assignment assignment) {
		super();
		this.id = id;
		this.broughtAt = broughtAt;
		this.quantity = quantity;
		this.price = price;
		this.sold = sold;
		this.soldInt = soldInt;
		this.material = material;
		this.assignment = assignment;
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

	public List<SellBuy> getSellBuys() {
		return sellbuys;
	}

	public void setSellBuys(List<SellBuy> sellbuys) {
		this.sellbuys = sellbuys;
	}
	
	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
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
	
	public Integer getTempQuantity() {
		return tempQuantity;
	}

	public void setTempQuantity(Integer tempQuantity) {
		this.tempQuantity = tempQuantity;
	}
	
	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public void setSellbuys(List<SellBuy> sellbuys) {
		this.sellbuys = sellbuys;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@PrePersist
	@PreUpdate
	public void setTotal() {
		totalPrice = price * quantity;
		tempQuantity = soldInt;
	}
	
}
