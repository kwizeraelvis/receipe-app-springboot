package com.elvis.springapp.springrecipe.Service;

import java.util.Optional;

import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elvis.springapp.springrecipe.commands.IngridientCommand;
import com.elvis.springapp.springrecipe.converters.IngredientCommandToIngredient;
import com.elvis.springapp.springrecipe.converters.IngredientToIngredientCommand;
import com.elvis.springapp.springrecipe.domain.Ingridient;
import com.elvis.springapp.springrecipe.domain.Recipe;
import com.elvis.springapp.springrecipe.repositories.RecipeRepository;
import com.elvis.springapp.springrecipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService{
	
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientToIngredientCommand converter;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
	public IngredientServiceImpl(RecipeRepository recipeRepository,
			IngredientToIngredientCommand converter,
			UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.converter = converter;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}
	
	@Override
	public IngridientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipOptional = recipeRepository.findById(recipeId);
		if(!recipOptional.isPresent()) {
			log.error("No recipe available with id : {}", recipeId);
		}
		Recipe savedRecipe = recipOptional.get();
		Optional<IngridientCommand> ingridientCommandOptional = savedRecipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> converter.convert(ingredient)).findFirst();
		if(!ingridientCommandOptional.isPresent()) {
			log.error("No ingredient exists with id : {}", ingredientId);
		}
		return ingridientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngridientCommand saveIngridientCommand(IngridientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		if(!recipeOptional.isPresent()) {
			log.error("No recipe with id {}",command.getId());
			return new IngridientCommand();
		}else {
			Recipe recipe = recipeOptional.get();
			Optional<Ingridient> ingredientOptional = recipe
					.getIngredients()
					.stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId()))
					.findFirst();
			if (ingredientOptional.isPresent()) {
				Ingridient foundIngridient = ingredientOptional.get();
				foundIngridient.setDescription(command.getDescription());
				foundIngridient.setUom(unitOfMeasureRepository
						.findById(command.getUom().getId())
						.orElseThrow(() -> new RuntimeException("NOT FOUND")));
			}else {
				recipe.addIngridient(ingredientCommandToIngredient.convert(command));
			}
			Recipe savedRecipe = recipeRepository.save(recipe);
			return converter.convert(savedRecipe
					.getIngredients()
					.stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
					.findFirst()
					.get());
		}
	}

}
