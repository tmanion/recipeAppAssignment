package com.tony.recipeapp.repositories;

import com.tony.recipeapp.domain.Recipe;

import org.springframework.data.repository.CrudRepository;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
