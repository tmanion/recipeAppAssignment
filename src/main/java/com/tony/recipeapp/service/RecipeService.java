package com.tony.recipeapp.service;

import com.tony.recipeapp.domain.Recipe;

import java.util.Set;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public interface RecipeService {
    /**
     * Returns the recipes value.
     *
     * @return  recipes value.
     */
    Set<Recipe> getRecipes();
}
