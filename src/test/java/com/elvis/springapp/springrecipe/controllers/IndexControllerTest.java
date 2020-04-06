package com.elvis.springapp.springrecipe.controllers;

import com.elvis.springapp.springrecipe.Service.ReceipeServiceImpl;
import com.elvis.springapp.springrecipe.Service.RecipeService;
import com.elvis.springapp.springrecipe.domain.Recipe;
import com.elvis.springapp.springrecipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController controller;
    @Mock
    RecipeService receipeService;
    @Mock
    Model model;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(receipeService);
    }


    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        Set<Recipe> recipes =  new HashSet<>();
        recipes.add(new Recipe());
        Recipe p = new Recipe();
        p.setId(1l);
        recipes.add(p);

        when(receipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String result = "index";
        assertEquals(result, controller.getIndexPage(model));
        verify(receipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recepies"), argumentCaptor.capture());
        Set<Recipe> indexRecipe = argumentCaptor.getValue();
        assertEquals(2, indexRecipe.size());
    }
}