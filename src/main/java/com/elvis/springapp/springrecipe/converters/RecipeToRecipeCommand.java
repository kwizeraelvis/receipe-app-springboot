package com.elvis.springapp.springrecipe.converters;

import com.elvis.springapp.springrecipe.commands.RecipeCommand;
import com.elvis.springapp.springrecipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter,
                                 CategoryToCategoryCommand categoryConverter,
                                 IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null){
            return null;
        }
        final RecipeCommand recipe = new RecipeCommand();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setSource(source.getSource());
        recipe.setServings(source.getServings());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setNotes(notesConverter.convert(source.getNotes()));
        if(source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }
        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingridient -> recipe.getIngredients().add(ingredientConverter.convert(ingridient)));
        }
        return recipe;
    }
}
