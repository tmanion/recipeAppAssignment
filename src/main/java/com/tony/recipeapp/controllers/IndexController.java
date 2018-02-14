package com.tony.recipeapp.controllers;

import com.tony.recipeapp.domain.Category;
import com.tony.recipeapp.domain.UnitOfMeasure;
import com.tony.recipeapp.repositories.CategoryRepository;
import com.tony.recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Controller public class IndexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    /**
     * Creates a new IndexController object.
     *
     * @param  categoryRepository
     * @param  unitOfMeasureRepository
     */
    public IndexController(final CategoryRepository categoryRepository,
        final UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    /**
     * Returns the index page value.
     *
     * @return  index page value.
     */
    @RequestMapping({ "", "/", "/index" })
    public String getIndexPage() {
        final Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        final Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat Id is: " + categoryOptional.get().getId());
        System.out.println("UoM Id is: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
