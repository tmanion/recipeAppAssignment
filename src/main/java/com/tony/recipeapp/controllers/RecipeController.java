package com.tony.recipeapp.controllers;

import com.tony.recipeapp.commands.RecipeCommand;
import com.tony.recipeapp.service.RecipeService;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Controller public class RecipeController {
    private final RecipeService recipeService;

    /**
     * Creates a new RecipeController object.
     *
     * @param  recipeService
     */
    public RecipeController(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   id
     *
     * @return  String
     */
    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable final String id) {
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   model
     *
     * @return  String
     */
    @GetMapping("recipe/new")
    public String newRecipe(final Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   command
     *
     * @return  String
     */
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute final RecipeCommand command) {
        final RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   id
     * @param   model
     *
     * @return  String
     */
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable final String id, final Model model) {
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   id
     * @param   model
     *
     * @return  String
     */
    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable final String id, final Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeform";
    }
}
