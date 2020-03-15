package com.elvis.springapp.springrecipe.controllers;

import com.elvis.springapp.springrecipe.domain.Category;
import com.elvis.springapp.springrecipe.domain.UnitOfMeasure;
import com.elvis.springapp.springrecipe.repositories.CategoryRepository;
import com.elvis.springapp.springrecipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;


    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
        Optional<Category> categoryOptional = categoryRepository.findByDescription("AMERICAN");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Ounce");

        System.out.println("Categoru Id is "  + categoryOptional.get().getId());
        System.out.println("UnitOfMeasure Id is " + unitOfMeasureOptional.get().getId());
        return "index";
    }
}
