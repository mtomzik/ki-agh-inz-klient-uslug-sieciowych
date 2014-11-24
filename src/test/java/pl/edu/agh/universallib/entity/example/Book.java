package pl.edu.agh.universallib.entity.example;

import java.util.List;

import pl.edu.agh.universallib.entity.Entity;

public class Book extends Entity {
	private String title;
	private List<String> author;
	private int year;
	private float price;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthor() {
		return author;
	}

	public void setAuthor(List<String> author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
