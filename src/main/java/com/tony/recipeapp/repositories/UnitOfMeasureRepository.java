package com.tony.recipeapp.repositories;

import com.tony.recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    /**
     * DOCUMENT ME!
     *
     * @param   description
     *
     * @return  Optional
     */
    Optional<UnitOfMeasure> findByDescription(String description);
}
