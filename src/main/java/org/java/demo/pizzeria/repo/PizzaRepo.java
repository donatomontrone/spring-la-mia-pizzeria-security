package org.java.demo.pizzeria.repo;

import java.util.List;

import org.java.demo.pizzeria.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Integer>{

public List<Pizza> findByNameContaining(String name);
}