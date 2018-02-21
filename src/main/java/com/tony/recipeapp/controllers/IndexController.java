package com.tony.recipeapp.controllers;

import com.tony.recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Slf4j
@Controller public class IndexController {
    private final RecipeService recipeService;

    /**
     * Creates a new IndexController object.
     *
     * @param  recipeService
     */
    public IndexController(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Returns the index page value.
     *
     * @param   model
     *
     * @return  index page value.
     */
    @RequestMapping({ "", "/", "/index" })
    public String getIndexPage(final Model model) {
        log.debug("Getting Index Page");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
