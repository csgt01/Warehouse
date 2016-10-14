package de.csgt.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SellBuy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Calendar createdAt;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Buy buy;
	
	@NotNull
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Sell sell;
	
	public SellBuy() {
		super();
	}

	public SellBuy(Sell sell, Buy buy, Integer quantity) {
		super();
		this.sell = sell;
		this.buy = buy;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Buy getBuy() {
		return buy;
	}

	public void setBuy(Buy buy) {
		this.buy = buy;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"quantity\":\"" + quantity + "\", \"buy\":\"" + buy.getId() + "\"}";
	}
	

	
}
