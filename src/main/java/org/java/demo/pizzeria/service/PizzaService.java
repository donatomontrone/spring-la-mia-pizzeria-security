package org.java.demo.pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.demo.pizzeria.pojo.Pizza;
import org.java.demo.pizzeria.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepository;
	
	public List<Pizza> findAll() {
		
		return pizzaRepository.findAll();
	}
	public Pizza save(Pizza pizza) {
		
		return pizzaRepository.save(pizza);
	}
	public Optional<Pizza> findById(Integer id) {
		
		return pizzaRepository.findById(id);
	}
	public List<Pizza> findBySearch(String name ){
		return pizzaRepository.findByNameContaining(name);
	}
	
	@Transactional
	public Optional<Pizza> findByIdwithSpecialOffer(Integer id) {
	Optional<Pizza> oPizza = pizzaRepository.findById(id);
	Hibernate.initialize(oPizza.get().getSpecialOffers());
	
	return oPizza;
	}
	
	@Transactional
	public Optional<Pizza> findByIdwithIngredients(Integer id) {
	Optional<Pizza> oPizza = pizzaRepository.findById(id);
	Hibernate.initialize(oPizza.get().getIngredients());
	
	return oPizza;
	}
	
	@Transactional
	public Optional<Pizza> findByIdwithIngredientsAndOffer(Integer id) {
	Optional<Pizza> oPizza = pizzaRepository.findById(id);
	Hibernate.initialize(oPizza.get().getIngredients());
	Hibernate.initialize(oPizza.get().getSpecialOffers());
	
	return oPizza;
	}
	
	public void delete(Pizza pizza) {
		pizzaRepository.delete(pizza);
	}
}