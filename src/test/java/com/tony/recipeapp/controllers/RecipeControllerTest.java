package com.tony.recipeapp.controllers;

import com.tony.recipeapp.domain.Recipe;
import com.tony.recipeapp.service.RecipeService;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyLong;

import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class RecipeControllerTest {
    RecipeController recipeController;

    @Mock RecipeService recipeService;

    /**
     * Creates a new RecipeControllerTest object.
     */
    public RecipeControllerTest() {
    }

    /**
     * Sets the up value.
     *
     * @throws  Exception
     */
    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testGetRecipe() throws Exception {
        final Recipe recipe = new Recipe();
        recipe.setId(1L);

        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("/recipe/show"))
            .andExpect(model().attributeExists("recipe"));

    }
}
