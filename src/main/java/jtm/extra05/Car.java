package jtm.extra05;

public class Car {

	private String model;
	private Integer year;
	private String color;
	private Float price;

	public Car(String model, Integer year, String color, Float price) {
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public Integer getYear() {
		return year;
	}

	public String getColor() {
		return color;
	}

	public Float getPrice() {
		return price;
	}

	// #1 generate public constructor which takes all properties of an
	// object as parameters

	// #2 generate public getters of all object properties
}
