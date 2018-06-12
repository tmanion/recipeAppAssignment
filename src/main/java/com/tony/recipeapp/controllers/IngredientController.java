package com.tony.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;

import com.tony.recipeapp.commands.IngredientCommand;
import com.tony.recipeapp.commands.RecipeCommand;
import com.tony.recipeapp.commands.UnitOfMeasureCommand;
import com.tony.recipeapp.service.IngredientService;
import com.tony.recipeapp.service.RecipeService;
import com.tony.recipeapp.service.UnitOfMeasureService;

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
@Slf4j @Controller public class IngredientController {
    IngredientService ingredientService;
    RecipeService recipeService;
    UnitOfMeasureService unitOfMeasureService;

    /**
     * Creates a new IngredientController object.
     *
     * @param  ingredientService
     * @param  recipeService
     * @param  unitOfMeasureService
     */
    public IngredientController(final IngredientService ingredientService, final RecipeService recipeService,
        final UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   recipeId
     * @param   ingredientId
     *
     * @return  String
     */
    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable final Long recipeId, @PathVariable final Long ingredientId) {
        log.debug("deleting ingredient id: " + ingredientId);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   recipeId
     * @param   model
     *
     * @return  String
     */
    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable final String recipeId, final Model model) {
        log.debug("Getting ingredients list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   recipeId
     * @param   model
     *
     * @return  String
     */
    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable final String recipeId, final Model model) {
        // Make sure id value is good
        final RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        // todo: raise exception if recipeCommand is null

        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        // init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   command
     *
     * @return  String
     */
    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute final IngredientCommand command) {
        final IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id: " + savedCommand.getRecipeId());
        log.debug("saved ingredient id: " + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   recipeId
     * @param   id
     * @param   model
     *
     * @return  String
     */
    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable final String recipeId, @PathVariable final String id,
        final Model model) {
        model.addAttribute("ingredient",
            ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        return "recipe/ingredient/show";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   recipeId
     * @param   id
     * @param   model
     *
     * @return  String
     */
    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable final String recipeId, @PathVariable final String id,
        final Model model) {
        model.addAttribute("ingredient",
            ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }
}
