package com.tony.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Entity public class UnitOfMeasure {
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Creates a new UnitOfMeasure object.
     */
    public UnitOfMeasure() {
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
}
