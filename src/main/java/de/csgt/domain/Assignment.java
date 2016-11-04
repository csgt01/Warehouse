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
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String orderNumber;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Calendar createdAt;
	
	@NotNull
	private Date orderedAt;

	private boolean closed = false;
	
	@NotNull
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Shop shop;
	
	@OneToMany(mappedBy = "assignment")
	@Cascade(value = {CascadeType.ALL})
	private List<Buy> buys;
	
//	@OneToMany(mappedBy = "assignment")
//	@Cascade(value = {CascadeType.ALL})
//	private List<AdditionalItems> additionalItems;
	private Double addtionalCosts;
	
	public Assignment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		double p = 0.0;
		if (buys != null) {
			for (Buy buy : buys) {
				p += buy.getTotalPrice();
			}
		}
		return p;
	}

	public Date getOrderedAt() {
		return orderedAt;
	}
	
	public void setOrderedAt(Date broughtAt) {
		this.orderedAt = broughtAt;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Buy> getBuys() {
		return buys;
	}

	public void setBuys(List<Buy> buys) {
		this.buys = buys;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getAddtionalCosts() {
		return addtionalCosts;
	}

	public void setAddtionalCosts(Double addtionalCosts) {
		this.addtionalCosts = addtionalCosts;
	}
	
}
