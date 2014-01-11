package com.srikanth.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Use: schemagen Product.java at command prompt.

@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

	@XmlElement(required=true)
	protected int id;
	@XmlElement(required=true)
	protected String name;
	@XmlElement(required=true)
	protected String description;
	@XmlElement(required=true)
	protected int price;
	
	public Product() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
