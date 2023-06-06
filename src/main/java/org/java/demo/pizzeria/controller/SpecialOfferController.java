package org.java.demo.pizzeria.controller;

import java.util.Optional;

import org.java.demo.pizzeria.pojo.Pizza;
import org.java.demo.pizzeria.pojo.SpecialOffer;
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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class SpecialOfferController {
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/special-offers/create")
	public String create(@RequestParam(name = "id") Integer pizzaId, Model model) {
		
		SpecialOffer specialOffer = new SpecialOffer();
		
		Pizza pizza = pizzaService.findById(pizzaId).get();
		
		specialOffer.setPizza(pizza);
		
		model.addAttribute("specialOffer", specialOffer);
		
		return "create-so";
	}
	
	@PostMapping("/special-offers/create")
	public String store(@Valid @ModelAttribute SpecialOffer specialOffer,
		BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("specialOffer", specialOffer);
			model.addAttribute("errors", bindingResult);
				return "create-so";
		}
		specialOfferService.save(specialOffer);
		Integer pizzaId = specialOffer.getPizza().getId();
		return "redirect:/pizzas/" + pizzaId;
	}
	
	@GetMapping("/special-offers/edit/{id}")
	public String edit(Model model, 
			@PathVariable("id") Integer id) {
		
		Optional<SpecialOffer> spOpt = specialOfferService.findById(id);
		SpecialOffer specialOffer = spOpt.get();

		model.addAttribute("specialOffer", specialOffer);
		
		return "edit-so";
	}

	@PostMapping("/special-offers/edit/{id}")
	public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute SpecialOffer specialOffer,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult);
			model.addAttribute("specialOffer", specialOffer);
			return "edit-so";
		}

		specialOfferService.save(specialOffer);
		Integer pizzaId = specialOffer.getPizza().getId();
		return "redirect:/pizzas/" + pizzaId;
	}
	
	
}
