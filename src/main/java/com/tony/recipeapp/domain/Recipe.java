package com.tony.recipeapp.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Entity public class Recipe {
    private Integer cookTime;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob private Byte[] image;
    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "recipe"
    ) // mappedBy refers to property name in ingredient object
    private Set<Ingredient> ingredients;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    private Integer prepTime;
    private Integer servings;
    private String source;
    private String url;

    /**
     * Creates a new Recipe object.
     */
    public Recipe() {
    }

    /**
     * Returns the cook time value.
     *
     * @return  cook time value.
     */
    public Integer getCookTime() {
        return cookTime;
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
     * Returns the difficulty value.
     *
     * @return  difficulty value.
     */
    public Difficulty getDifficulty() {
        return difficulty;
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
     * Returns the image value.
     *
     * @return  image value.
     */
    public Byte[] getImage() {
        return image;
    }

    /**
     * Returns the ingredients value.
     *
     * @return  ingredients value.
     */
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Returns the notes value.
     *
     * @return  notes value.
     */
    public Notes getNotes() {
        return notes;
    }

    /**
     * Returns the prep time value.
     *
     * @return  prep time value.
     */
    public Integer getPrepTime() {
        return prepTime;
    }

    /**
     * Returns the servings value.
     *
     * @return  servings value.
     */
    public Integer getServings() {
        return servings;
    }

    /**
     * Returns the source value.
     *
     * @return  source value.
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the url value.
     *
     * @return  url value.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the cook time value.
     *
     * @param  cookTime
     */
    public void setCookTime(final Integer cookTime) {
        this.cookTime = cookTime;
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
     * Sets the difficulty value.
     *
     * @param  difficulty
     */
    public void setDifficulty(final Difficulty difficulty) {
        this.difficulty = difficulty;
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
     * Sets the image value.
     *
     * @param  image
     */
    public void setImage(final Byte[] image) {
        this.image = image;
    }

    /**
     * Sets the ingredients value.
     *
     * @param  ingredients
     */
    public void setIngredients(final Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Sets the notes value.
     *
     * @param  notes
     */
    public void setNotes(final Notes notes) {
        this.notes = notes;
    }

    /**
     * Sets the prep time value.
     *
     * @param  prepTime
     */
    public void setPrepTime(final Integer prepTime) {
        this.prepTime = prepTime;
    }

    /**
     * Sets the servings value.
     *
     * @param  servings
     */
    public void setServings(final Integer servings) {
        this.servings = servings;
    }

    /**
     * Sets the source value.
     *
     * @param  source
     */
    public void setSource(final String source) {
        this.source = source;
    }

    /**
     * Sets the url value.
     *
     * @param  url
     */
    public void setUrl(final String url) {
        this.url = url;
    }
}
