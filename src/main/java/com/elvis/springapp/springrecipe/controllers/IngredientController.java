package com.elvis.springapp.springrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elvis.springapp.springrecipe.Service.IngredientService;
import com.elvis.springapp.springrecipe.Service.RecipeService;
import com.elvis.springapp.springrecipe.Service.UnitOfMeasureService;
import com.elvis.springapp.springrecipe.commands.IngridientCommand;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable String recipeId, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
		return "recipe/ingredient/list";
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
	public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		return "recipe/ingredient/show";
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		model.addAttribute("uomList", unitOfMeasureService.listAllUoMs());
		return "recipe/ingredient/ingredientform";
	}

	@PostMapping
	@RequestMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngridientCommand command) {
		IngridientCommand savedCommand = ingredientService.saveIngridientCommand(command);
		log.info("recipe id is : {}\n", savedCommand.getRecipeId());
		log.info("ingredient id is : {}\n", savedCommand.getId());
		return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
	}

}