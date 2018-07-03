package com.tony.recipeapp.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Slf4j @Service public class ImageServiceImpl implements ImageService {
    /**
     * Creates a new ImageServiceImpl object.
     */
    public ImageServiceImpl() {
    }

    @Override public void saveImageFile(final Long id, final MultipartFile file) {
        log.debug("Received a file");
    }
}
