package com.tony.recipeapp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Data
@Entity public class UnitOfMeasure {
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
