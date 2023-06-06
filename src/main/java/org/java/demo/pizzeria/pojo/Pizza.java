package org.java.demo.pizzeria.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="Inserisci il nome della pizza.")
	@Size(min=3,max=255,message="Il nome della pizza deve essere compreso tra 3 e 255 caratteri")
	private String name;
	
	@Column(columnDefinition = "text")
	@NotBlank(message="Inserisci la descrizione della pizza.")
	@Size(min=3, message="La descrizione della pizza deve essere almeno di 3 caratteri.")
	private String description;
	
	@URL(message = "Inserisci un url per l'immagine della pizza valido.")
	@NotBlank(message="Inserisci il link per l'immagine della pizza.")
	@Size(max=255,message="Il nome della pizza non può superare i 255 caratteri.")
	private String imgPath;
	
	@Min(value=3,message="Il prezzo della pizza deve essere almeno di 3,00€.")
	@NotNull(message="Inserisci il prezzo della pizza.")
	@Max(value=25,message="Il prezzo della pizza non può superare i 25,00€.")
	private Integer price;
	
	@OneToMany(mappedBy = "pizza")
	private List<SpecialOffer> specialOffers;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	public Pizza() {}

	public Pizza(String name, String description, String imgPath, Integer price, Ingredient... ingredients) {
		setName(name);
		setDescription(description);
		setImgPath(imgPath);
		setPrice(price);
		setIngredients(ingredients);
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	//Con questa funzione possiamo passare un array e trasformarlo poi in una Collection di Ingredients
	public void setIngredients(Ingredient[] ingredients) {

		setIngredients(Arrays.asList(ingredients));
	}
	
	public void addIngredient(Ingredient ingredient) {
		getIngredients().add(ingredient);
	}
	
	public void removeIngredient(Ingredient ingredient) {
		getIngredients().remove(ingredient);
	}

	@Override
	public String toString() {
		return "[" + getId() + "] - " + getName() + " | " + getPrice() + "€"
			+ "\nDescrizione: " + getDescription();
	}
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Pizza)) return false;

		Pizza pizzaObject = (Pizza) obj;

		return getId() == pizzaObject.getId();
	}
	@Override
	public int hashCode() {

		return getId();
	}
	
}
