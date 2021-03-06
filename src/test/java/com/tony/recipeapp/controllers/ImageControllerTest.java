package com.tony.recipeapp.controllers;

import com.tony.recipeapp.commands.RecipeCommand;
import com.tony.recipeapp.service.ImageService;
import com.tony.recipeapp.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public class ImageControllerTest {
    ImageController controller;

    @Mock ImageService imageService;

    MockMvc mockMvc;

    @Mock RecipeService recipeService;

    /**
     * Creates a new ImageControllerTest object.
     */
    public ImageControllerTest() {
    }

    /**
     * Returns the image form value.
     *
     * @throws  Exception
     */
    @Test public void getImageForm() throws Exception {
        // given
        final RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        // when
        mockMvc.perform(get("/recipe/1/image")).andExpect(status().isOk()).andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());

    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception
     */
    @Test public void handleImagePost() throws Exception {
        final MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile)).andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    /**
     * Sets the up value.
     *
     * @throws  Exception
     */
    @Before public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test public void renderImageFromDB() throws Exception{
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primeByte : s.getBytes()) {
            bytesBoxed[i++] = primeByte;
        }

        command.setImage(bytesBoxed);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responceBytes = response.getContentAsByteArray();
        assertEquals(s.getBytes().length, responceBytes.length);
    }

    @Test public void testGetImageNumberFormatException() throws Exception {
        mockMvc.perform(get("/recipe/asdf/recipeimage"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }


}
