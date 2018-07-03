package com.tony.recipeapp.service;

import lombok.extern.slf4j.Slf4j;

import com.tony.recipeapp.domain.Recipe;
import com.tony.recipeapp.repositories.RecipeRepository;

import java.io.IOException;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Slf4j @Service public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    /**
     * Creates a new ImageServiceImpl object.
     *
     * @param  recipeRepository
     */
    public ImageServiceImpl(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override public void saveImageFile(final Long recipeId, final MultipartFile file) {
        try {
            final Recipe recipe = recipeRepository.findById(recipeId).get();

            final Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (final byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            // todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
