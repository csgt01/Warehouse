package de.csgt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class AdditionalItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private Double price;
	private Double totalPrice;
	private Integer quantity;
	
//	@ManyToOne
//	@JoinColumn(referencedColumnName = "id")
//	private Assignment assignment;
//	
//	@ManyToOne
//	@JoinColumn(referencedColumnName = "id")
//	private Sell sell;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


//	public Assignment getAssignment() {
//		return assignment;
//	}
//
//
//	public void setAssignment(Assignment assignment) {
//		this.assignment = assignment;
//	}


	@PrePersist
	@PreUpdate
	public void setTotal() {
		totalPrice = price * quantity;
	}

}
