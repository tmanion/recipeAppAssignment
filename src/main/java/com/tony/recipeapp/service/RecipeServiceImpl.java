package com.tony.recipeapp.service;

import com.tony.recipeapp.domain.Recipe;
import com.tony.recipeapp.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Service public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    /**
     * Creates a new RecipeServiceImpl object.
     *
     * @param  recipeRepository
     */
    @Autowired public RecipeServiceImpl(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override public Set<Recipe> getRecipes() {
        final Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;
    }
}
