package org.java.demo.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pizzeria.pojo.Ingredient;
import org.java.demo.pizzeria.pojo.Pizza;
import org.java.demo.pizzeria.pojo.SpecialOffer;
import org.java.demo.pizzeria.service.IngredientService;
import org.java.demo.pizzeria.service.PizzaService;
import org.java.demo.pizzeria.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("pizzas", pizzas);
		
		return "index";
	}
	@PostMapping
	public String getIndexByName(Model model, @RequestParam(required = false ) String name) {
		List<Pizza> pizzas = pizzaService.findBySearch(name);
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("name", name);
		
		return "index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, 
			@PathVariable("id") Integer id) {
		
		Optional<Pizza> oPizza = pizzaService.findByIdwithIngredientsAndOffer(id);
		Pizza pizza = oPizza.get();
		List<SpecialOffer> specialOffers = pizza.getSpecialOffers();
		List<Ingredient> ingredients = pizza.getIngredients();
		
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("pizza", pizza);
		return "pizza";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", ingredients);
		return "create-pizza";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute Pizza pizza,
		BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
				model.addAttribute("errors", bindingResult);
				model.addAttribute("pizza", pizza);
				return "create-pizza";
		}
		
		pizzaService.save(pizza);

		return "redirect:/pizzas/" + pizza.getId();
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, 
			@PathVariable("id") Integer id) {
		
		Optional<Pizza> oPizza = pizzaService.findByIdwithIngredients(id);
		Pizza pizza = oPizza.get();
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "edit-pizza";
	}

	@PostMapping("/edit/{id}")
	public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult);
			model.addAttribute("pizza", pizza);
			return "edit-pizza";
		}

		pizzaService.save(pizza);
		return "redirect:/pizzas/{id}";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Optional<Pizza> pizzaOpt = pizzaService.findByIdwithIngredientsAndOffer(id);
		Pizza pizza = pizzaOpt.get();

	
		pizzaService.delete(pizza);
		return "redirect:/pizzas";
	}
	
	
}
