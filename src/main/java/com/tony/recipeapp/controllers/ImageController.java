package com.tony.recipeapp.controllers;

import com.tony.recipeapp.service.ImageService;
import com.tony.recipeapp.service.RecipeService;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Controller public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    /**
     * Creates a new ImageController object.
     *
     * @param  imageService
     * @param  recipeService
     */
    public ImageController(final ImageService imageService, final RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   id
     * @param   file
     *
     * @return  String
     */
    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable final String id,
        @RequestParam("imagefile") final MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";
    }

    /**
     * DOCUMENT ME!
     *
     * @param   id
     * @param   model
     *
     * @return  String
     */
    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable final String id, final Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }
}
