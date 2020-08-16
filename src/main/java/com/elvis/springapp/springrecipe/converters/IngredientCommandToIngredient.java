package com.elvis.springapp.springrecipe.converters;

import com.elvis.springapp.springrecipe.commands.IngridientCommand;
import com.elvis.springapp.springrecipe.domain.Ingridient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngridientCommand, Ingridient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingridient convert(IngridientCommand source) {
        if(source == null){
            return null;
        }
        final Ingridient ingridient = new Ingridient();
        ingridient.setId(source.getId());
        ingridient.setDescription(source.getDescription());
        ingridient.setAmount(source.getAmount());
        ingridient.setUom(uomConverter.convert(source.getUom()));
        return ingridient;
    }
}
