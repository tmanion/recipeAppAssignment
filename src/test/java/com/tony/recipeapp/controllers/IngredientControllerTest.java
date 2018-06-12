package com.tony.recipeapp.controllers;

import com.tony.recipeapp.commands.IngredientCommand;
import com.tony.recipeapp.commands.RecipeCommand;
import com.tony.recipeapp.service.IngredientService;
import com.tony.recipeapp.service.RecipeService;
import com.tony.recipeapp.service.UnitOfMeasureService;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class IngredientControllerTest {
    IngredientController controller;

    @Mock IngredientService ingredientService;

    MockMvc mockMvc;

    @Mock RecipeService recipeService;

    @Mock UnitOfMeasureService unitOfMeasureService;

    /**
     * Creates a new IngredientControllerTest object.
     */
    public IngredientControllerTest() {
    }

    /**
     * Sets the up value.
     *
     * @throws  Exception
     */
    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(ingredientService, recipeService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testDeleteIngredient() throws Exception {
        // then
        mockMvc.perform(get("/recipe/2/ingredient/3/delete")).andExpect(status().is3xxRedirection()).andExpect(view()
            .name("redirect:/recipe/2/ingredients"));

        verify(ingredientService, times(1)).deleteById(anyLong(), anyLong());

    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testListIngredients() throws Exception {
        // given
        final RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        // when
        mockMvc.perform(get("/recipe/1/ingredients")).andExpect(status().isOk()).andExpect(view().name(
                "recipe/ingredient/list")).andExpect(model().attributeExists("recipe"));

        // then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testNewIngredientForm() throws Exception {
        // given
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        // when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        // then
        mockMvc.perform(get("/recipe/1/ingredient/new")).andExpect(status().isOk()).andExpect(view().name(
                "recipe/ingredient/ingredientform")).andExpect(model().attributeExists("ingredient")).andExpect(model()
            .attributeExists("uomList"));

        verify(recipeService, times(1)).findCommandById(anyLong());

    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testSaveOrUpdate() throws Exception {
        // given
        final IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        // when
        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        // then
        mockMvc.perform(post("/recipe/2/ingredient").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "")
            .param("description", "some string")).andExpect(status().is3xxRedirection()).andExpect(view().name(
                "redirect:/recipe/2/ingredient/3/show"));

    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testShowIngredient() throws Exception {
        // given
        final IngredientCommand ingredientCommand = new IngredientCommand();

        // when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        // then
        mockMvc.perform(get("/recipe/1/ingredient/2/show")).andExpect(status().isOk()).andExpect(view().name(
                "recipe/ingredient/show")).andExpect(model().attributeExists("ingredient"));
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testUpdateIngredientForm() throws Exception {
        // given
        final IngredientCommand ingredientCommand = new IngredientCommand();

        // when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        // then
        mockMvc.perform(get("/recipe/1/ingredient/2/update")).andExpect(status().isOk()).andExpect(view().name(
                "recipe/ingredient/ingredientform")).andExpect(model().attributeExists("ingredient")).andExpect(model()
            .attributeExists("uomList"));
    }
}
