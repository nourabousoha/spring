package fr.eservices.drive.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
@Inheritance
@Entity
public class Article {
	@Id
	@GeneratedValue
	private Long id;
	private String
	name,
	img;
	@Column(length=13)
	private String ean;

	private int price;
	@Column(columnDefinition="Decimal(10,2) default '20.00'")
	private double vat;
	@ManyToMany
	private List<Category> categories;
	public List<Category> getCategories() {
		return categories;
	}

	public String getEAN() {
		return ean;
	}

	public Long getId() {
		return id;
	}
    
	public String getImg() {
		return img;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public double getVat() {
		return vat;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setEAN(String eAN) {
		ean = eAN;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setImg(String img) {
		this.img = img;
	}

    public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}



}
