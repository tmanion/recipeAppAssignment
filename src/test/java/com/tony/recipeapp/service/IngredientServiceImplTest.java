package com.tony.recipeapp.service;

import com.tony.recipeapp.commands.IngredientCommand;
import com.tony.recipeapp.converters.IngredientCommandToIngredient;
import com.tony.recipeapp.converters.IngredientToIngredientCommand;
import com.tony.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.tony.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.tony.recipeapp.domain.Ingredient;
import com.tony.recipeapp.domain.Recipe;
import com.tony.recipeapp.repositories.RecipeRepository;
import com.tony.recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class IngredientServiceImplTest {
    IngredientService ingredientService;

    @Mock RecipeRepository recipeRepository;

    @Mock UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    /**
     * Creates a new IngredientServiceImplTest object.
     */
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(
                new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(
                new UnitOfMeasureCommandToUnitOfMeasure());
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void findByRecipeIdAndId() throws Exception {
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void findByRecipeIdAndIngredientIdHappyPath() throws Exception {
        // given
        final Recipe recipe = new Recipe();
        recipe.setId(1L);

        final Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        final Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        final Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        final Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // then
        final IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        // when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    /**
     * Sets the up value.
     *
     * @throws  Exception
     */
    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeRepository, unitOfMeasureRepository);
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testDeleteById() throws Exception {
        // given
        final Recipe recipe = new Recipe();
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);

        final Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // when
        ingredientService.deleteById(1L, 3L);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void testSaveRecipeCommand() throws Exception {
        final IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        final Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        final Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        final IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}
