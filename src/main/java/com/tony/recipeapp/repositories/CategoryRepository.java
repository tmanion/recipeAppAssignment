package com.tony.recipeapp.repositories;

import com.tony.recipeapp.domain.Category;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    /**
     * DOCUMENT ME!
     *
     * @param   description
     *
     * @return  Optional
     */
    Optional<Category> findByDescription(String description);
}
