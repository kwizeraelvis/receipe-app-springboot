package com.elvis.springapp.springrecipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String Source;
    private String url;
    @Lob
    private String directions;
    @Enumerated(value = EnumType.STRING)
    private Diffculty diffculty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingridient> ingredients = new HashSet<>();

    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;


    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngridient(Ingridient ingridient){
        ingridient.setRecipe(this);
        this.ingredients.add(ingridient);
        return this;
    }

}
