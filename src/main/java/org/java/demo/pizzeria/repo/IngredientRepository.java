package org.java.demo.pizzeria.repo;

import org.java.demo.pizzeria.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
