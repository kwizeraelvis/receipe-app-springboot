package com.elvis.springapp.springrecipe.commands;

import com.elvis.springapp.springrecipe.domain.Category;
import com.elvis.springapp.springrecipe.domain.Diffculty;
import com.elvis.springapp.springrecipe.domain.Ingridient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String Source;
    private String url;
    private String directions;
    private Diffculty diffculty;
    private Set<IngridientCommand> ingredients = new HashSet<>();
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
}
