package com.tony.recipeapp.controllers;

import com.tony.recipeapp.commands.IngredientCommand;
import com.tony.recipeapp.commands.RecipeCommand;
import com.tony.recipeapp.commands.UnitOfMeasureCommand;
import com.tony.recipeapp.service.IngredientService;
import com.tony.recipeapp.service.RecipeService;
import com.tony.recipeapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Slf4j @Controller public class IngredientController {
    IngredientService ingredientService;
    RecipeService recipeService;
    UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   recipeId
     * @param   model
     *
     * @return  String
     */
    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
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
     * @param   id
     * @param   model
     *
     * @return  String
     */
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
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
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable final String recipeId, @PathVariable final String id,
        final Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id: " + savedCommand.getRecipeId());
        log.debug("saved ingredient id: " + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model) {
        //Make sure id value is good
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        //todo: raise exception if recipeCommand is null

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);
        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }
}
