package org.java.demo.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pizzeria.pojo.Ingredient;
import org.java.demo.pizzeria.pojo.Pizza;
import org.java.demo.pizzeria.service.IngredientService;
import org.java.demo.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String index(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		return "index-ingredients";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("ingredient", new Ingredient());
		model.addAttribute("pizzas", pizzas);
		
		return "create-ingredient";
	}
	
	@PostMapping("/create")
	public String store(@ModelAttribute Ingredient ingredient) {
		
		if (ingredient.getPizzas() != null) {
			for (Pizza p : ingredient.getPizzas()) {
				Pizza tmpP = pizzaService.findByIdwithIngredients(p.getId()).get();
				tmpP.addIngredient(ingredient);
				pizzaService.save(tmpP);
			}
			
		}
		return "redirect:/ingredients";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Integer id) {
		Optional<Ingredient> optIngredient = ingredientService.findByIdwithPizzas(id);
		Ingredient ingredient = optIngredient.get();
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("ingredient", ingredient);
		
		return "edit-ingredient";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@ModelAttribute Ingredient ingredient) {
		
		ingredientService.save(ingredient);
		
		for (Pizza pizza : pizzaService.findAll()) {
			pizza.removeIngredient(ingredient);
			pizzaService.save(pizza);
		
		}
		
		if (ingredient.getPizzas() != null) {
			for (Pizza p : ingredient.getPizzas()) {
				Pizza tmpP = pizzaService.findByIdwithIngredients(p.getId()).get();
				tmpP.addIngredient(ingredient);
				pizzaService.save(tmpP);
			}
		}
		return "redirect:/ingredients";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Optional<Ingredient> optIngredient = ingredientService.findById(id);
		Ingredient ingredient = optIngredient.get();
		
		for (Pizza pizza : ingredient.getPizzas()) {
			pizza.removeIngredient(ingredient);
			pizzaService.save(pizza);
		}
		ingredientService.delete(ingredient);
		return "redirect:/ingredients";
	}
}
