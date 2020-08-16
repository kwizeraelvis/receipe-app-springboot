package com.elvis.springapp.springrecipe.Service;

import com.elvis.springapp.springrecipe.converters.RecipeCommandToRecipe;
import com.elvis.springapp.springrecipe.converters.RecipeToRecipeCommand;
import com.elvis.springapp.springrecipe.domain.Recipe;
import com.elvis.springapp.springrecipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ReceipeServiceImplTest {


    ReceipeServiceImpl receipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        receipeService = new ReceipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet Recipe = new HashSet();
        Recipe.add(recipe);
        when(receipeService.getRecipes()).thenReturn(Recipe);
        Set<Recipe> recipes = receipeService.getRecipes();
        assertEquals(recipes.size(), 1);
    }

    @Test
    public void testGetRecipes() {
        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        when(receipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = receipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }
}