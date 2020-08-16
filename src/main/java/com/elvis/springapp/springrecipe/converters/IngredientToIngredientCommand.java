package com.elvis.springapp.springrecipe.converters;

import com.elvis.springapp.springrecipe.commands.IngridientCommand;
import com.elvis.springapp.springrecipe.domain.Ingridient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingridient, IngridientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter) {
        this.unitOfMeasureConverter = unitOfMeasureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngridientCommand convert(Ingridient source) {
       if(source == null){
           return null;
       }
       final IngridientCommand ingridientCommand = new IngridientCommand();
       ingridientCommand.setId(source.getId());
       if(source.getRecipe() != null) {
    	   ingridientCommand.setRecipeId(source.getRecipe().getId());
       }
       ingridientCommand.setDescription(source.getDescription());
       ingridientCommand.setAmount(source.getAmount());
       ingridientCommand.setUom(unitOfMeasureConverter.convert(source.getUom()));
       return ingridientCommand;
    }
}
