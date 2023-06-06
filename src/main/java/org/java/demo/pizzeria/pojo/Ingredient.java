package org.java.demo.pizzeria.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@NotBlank(message="Inserisci il nome dell'ingrediente.")
	@Size(min=3,max=255, message="Il nome dell'ingrediente deve essere di almeno 3 caratteri fino a 255.")
	private String name;
	
	@Column(columnDefinition = "text")
	@Size(min = 0, max = 65536, message="La descrizione dell'ingrediente è troppo lunga.")
	private String description;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Pizza> pizzas;
	
	
	public Ingredient() {}
	
	public Ingredient(String name) {
		setName(name);
	}
	
	public Ingredient(String name, String description) {
		this(name);
		setDescription(description);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	public boolean  hasDescription() {
		return getDescription() != null;
	}

	
	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@Override
	public String toString() {
		return "Ingrediente: " + getName() + (hasDescription() ? " - Descrizione: " + getDescription() : "");
	}
	
	
	//Settare la equals servirà in fase di upgrade
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Ingredient)) return false;

		Ingredient ingObject = (Ingredient) obj;

		return getId() == ingObject.getId();
	}
	@Override
	public int hashCode() {

		return getId();
	}
}
