package fr.eservices.drive.repository;

import org.springframework.data.repository.CrudRepository;

import fr.eservices.drive.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
   Category findByName(String name);
}
