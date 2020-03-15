package com.elvis.springapp.springrecipe.repositories;

import com.elvis.springapp.springrecipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String Description);
}
