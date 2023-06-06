package org.java.demo.pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.demo.pizzeria.pojo.Ingredient;
import org.java.demo.pizzeria.pojo.Pizza;
import org.java.demo.pizzeria.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	public List<Ingredient> findAll(){
		return ingredientRepository.findAll();
	}
	
	public Optional<Ingredient> findById(Integer id) {
		return ingredientRepository.findById(id);
	}
	
	@Transactional
	public Optional<Ingredient> findByIdwithPizzas(Integer id) {
	Optional<Ingredient> optIng = ingredientRepository.findById(id);
	Hibernate.initialize(optIng.get().getPizzas());
	
	return optIng;
	}
	
	
	public Ingredient save(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	public void delete(Ingredient ingredient) {
		ingredientRepository.delete(ingredient);
	}
}
