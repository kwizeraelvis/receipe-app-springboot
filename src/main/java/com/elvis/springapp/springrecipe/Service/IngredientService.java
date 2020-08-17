package com.elvis.springapp.springrecipe.Service;

import com.elvis.springapp.springrecipe.commands.IngridientCommand;

public interface IngredientService {
	IngridientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	IngridientCommand saveIngridientCommand(IngridientCommand command);
	void deleteIngredientById(Long recipeId, Long ingredientId);
}
