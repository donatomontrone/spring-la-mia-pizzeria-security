package org.java.demo.pizzeria.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.AssertTrue;

@Entity
public class SpecialOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@FutureOrPresent(message="La data deve essere ugual o seguente alla data odierna.")
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@NotBlank(message="Inserisci il titolo dell'offerta speciale.")
	@Size(min=3,max=255,message="Il titolo dell'offerta speciale deve essere compreso tra 3 e 255 caratteri")
	private String title;
	
	@Min(value=1,message="La percentuale di sconto dell'offerta speciale non può essere inferiore all'1%.")
	@NotNull(message="Inserisci il prezzo della pizza.")
	@Max(value=100,message="La percentuale di sconto dell'offerta speciale non può superare il 100%.")
	private Integer discountPerc;
	
	@ManyToOne
	private Pizza pizza;
	
	public SpecialOffer() {}
	public SpecialOffer(LocalDate startDate, LocalDate endDate, String title, Integer discountPerc, Pizza pizza) {
		setStartDate(startDate);
		setEndDate(endDate);
		setTitle(title);
		setDiscountPerc(discountPerc);
		setPizza(pizza);
	}
	
	@AssertTrue(message="La data deve essere seguente alla data d'inizio dell'offerta.")
	private boolean FutureDate() {
		return getStartDate().isBefore(getEndDate());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getDiscountPerc() {
		return discountPerc;
	}
	public void setDiscountPerc(Integer discountPerc) {
		this.discountPerc = discountPerc;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String toString() {
		return "[" +  getId() + "] " + getTitle()
				+ "\nL'offerta inizia il: " + getStartDate() + " e termina " + getEndDate() + " - Pizza " + getPizza();
	}
	
		public String getDiscount(Integer price) {
			Double discount = price *  getDiscountPerc() / 100d;
			String finalPrice = String.format("%.2f€", price - discount);
			return finalPrice;
	}
}
