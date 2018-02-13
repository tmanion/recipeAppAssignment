package com.tony.recipeapp.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Entity public class Category {
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    /**
     * Creates a new Category object.
     */
    public Category() {
    }

    /**
     * Returns the description value.
     *
     * @return  description value.
     */
    public String getDescription() {
        return description;
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
     * Returns the recipes value.
     *
     * @return  recipes value.
     */
    public Set<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Sets the description value.
     *
     * @param  description
     */
    public void setDescription(final String description) {
        this.description = description;
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
     * Sets the recipes value.
     *
     * @param  recipes
     */
    public void setRecipes(final Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
