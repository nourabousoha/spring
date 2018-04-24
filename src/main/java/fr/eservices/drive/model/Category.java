package fr.eservices.drive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(unique=true ,nullable=false)
	private String name;
	private int orderIdx ;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getOrderIdx() {
		return orderIdx;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOrderIdx(int orderIdx) {
		this.orderIdx = orderIdx;
	}

}
