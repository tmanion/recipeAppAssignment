package com.tony.recipeapp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Entity public class Ingredient {
    private BigDecimal amount;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    /**
     * Creates a new Ingredient object.
     */

    public Ingredient() {
    }

    /**
     * Returns the amount value.
     *
     * @return  amount value.
     */
    public BigDecimal getAmount() {
        return amount;
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
     * Returns the recipe value.
     *
     * @return  recipe value.
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Returns the uom value.
     *
     * @return  uom value.
     */
    public UnitOfMeasure getUom() {
        return uom;
    }

    /**
     * Sets the amount value.
     *
     * @param  amount
     */
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
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
     * Sets the recipe value.
     *
     * @param  recipe
     */
    public void setRecipe(final Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Sets the uom value.
     *
     * @param  uom
     */
    public void setUom(final UnitOfMeasure uom) {
        this.uom = uom;
    }
}
