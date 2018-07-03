package com.tony.recipeapp.service;

import com.tony.recipeapp.domain.Recipe;
import com.tony.recipeapp.repositories.RecipeRepository;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.mock.web.MockMultipartFile;

import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class ImageServiceImplTest {
    ImageService imageService;
    @Mock RecipeRepository recipeRepository;

    /**
     * Creates a new ImageServiceImplTest object.
     */
    public ImageServiceImplTest() {
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void saveImageFile() throws Exception {
        // given
        final Long id = 1L;
        final MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());

        final Recipe recipe = new Recipe();
        recipe.setId(id);

        final Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        final ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        // when
        imageService.saveImageFile(id, multipartFile);

        // then
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());

        final Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }

    /**
     * Sets the up value.
     *
     * @throws  Exception
     */
    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(recipeRepository);
    }

}
