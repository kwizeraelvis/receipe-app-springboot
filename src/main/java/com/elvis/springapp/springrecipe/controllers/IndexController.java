package com.elvis.springapp.springrecipe.controllers;

import com.elvis.springapp.springrecipe.Service.RecipeService;
import com.elvis.springapp.springrecipe.domain.Category;
import com.elvis.springapp.springrecipe.domain.UnitOfMeasure;
import com.elvis.springapp.springrecipe.repositories.CategoryRepository;
import com.elvis.springapp.springrecipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private RecipeService recipeService;


    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recepies", recipeService.getRecipes());
        return "index";
    }
}
