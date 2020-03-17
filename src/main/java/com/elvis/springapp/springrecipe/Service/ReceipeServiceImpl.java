package com.elvis.springapp.springrecipe.Service;

import com.elvis.springapp.springrecipe.domain.Recipe;
import com.elvis.springapp.springrecipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReceipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public ReceipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
}
