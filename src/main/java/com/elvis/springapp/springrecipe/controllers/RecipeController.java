package com.elvis.springapp.springrecipe.controllers;

import com.elvis.springapp.springrecipe.Service.RecipeService;
import com.elvis.springapp.springrecipe.commands.RecipeCommand;

import org.hibernate.sql.Delete;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String findById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }
    
    @RequestMapping("/recipe/{id}/update")
    public String update(@PathVariable String id, Model model) {
    	model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
    	return "recipe/recipeform";
    }
    
    @RequestMapping("/recipe/{id}/delete")
    public String delete(@PathVariable String id) {
    	recipeService.deleteById(Long.valueOf(id));
    	return "redirect:/index";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveorupdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+savedCommand.getId()+"/show";
    }
}
