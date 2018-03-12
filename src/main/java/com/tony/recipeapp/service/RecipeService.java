package com.tony.recipeapp.service;

import com.tony.recipeapp.commands.RecipeCommand;
import com.tony.recipeapp.domain.Recipe;

import java.util.Set;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
