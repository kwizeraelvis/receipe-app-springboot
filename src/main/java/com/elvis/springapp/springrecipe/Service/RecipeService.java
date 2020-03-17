package com.elvis.springapp.springrecipe.Service;

import com.elvis.springapp.springrecipe.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface RecipeService {
    Set<Recipe> getRecipes();
}
