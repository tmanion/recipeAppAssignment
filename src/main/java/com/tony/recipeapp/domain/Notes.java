package com.tony.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Entity public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne private Recipe recipe;
    @Lob private String recipeNotes;

    /**
     * Creates a new Notes object.
     */
    public Notes() {
    }

    /**
     * Returns the id value.
     *
     * @return  id value.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the recipe value.
     *
     * @return  recipe value.
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Returns the recipe notes value.
     *
     * @return  recipe notes value.
     */
    public String getRecipeNotes() {
        return recipeNotes;
    }

    /**
     * Sets the id value.
     *
     * @param  id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Sets the recipe value.
     *
     * @param  recipe
     */
    public void setRecipe(final Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Sets the recipe notes value.
     *
     * @param  recipeNotes
     */
    public void setRecipeNotes(final String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
