package org.java.demo.pizzeria.auth.repo;

import org.java.demo.pizzeria.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
