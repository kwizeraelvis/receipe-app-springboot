package com.elvis.springapp.springrecipe.controllers;

import com.elvis.springapp.springrecipe.Service.RecipeService;
import com.elvis.springapp.springrecipe.domain.Recipe;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class FindByIdControllerTest {
//    FindByIdController findByIdController;
//
//    @Mock
//    RecipeService recipeService;
//
//    @Before
//    public void setUp() throws Exception{
//        MockitoAnnotations.initMocks(this);
//        findByIdController = new FindByIdController(recipeService);
//    }
//
//
//    @Test
//    public void testFindById() throws Exception {
//        Recipe recipe = new Recipe();
//        recipe.setId(1l);
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(findByIdController).build();
//        when(recipeService.findById(anyLong())).thenReturn(recipe);
//        mockMvc.perform(get("/recipe/show/1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("recipe/show"));
//    }
}