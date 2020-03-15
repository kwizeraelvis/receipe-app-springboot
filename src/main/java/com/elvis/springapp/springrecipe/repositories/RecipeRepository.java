package com.elvis.springapp.springrecipe.repositories;

import com.elvis.springapp.springrecipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
