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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uom;

    /**
     * Creates a new UnitOfMeasure object.
     */
    public UnitOfMeasure() {
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
     * Returns the uom value.
     *
     * @return  uom value.
     */
    public String getUom() {
        return uom;
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
     * Sets the uom value.
     *
     * @param  uom
     */
    public void setUom(final String uom) {
        this.uom = uom;
    }
}
