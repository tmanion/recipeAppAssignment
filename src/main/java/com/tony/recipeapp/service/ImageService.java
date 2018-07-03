package com.tony.recipeapp.service;

import org.springframework.web.multipart.MultipartFile;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
public interface ImageService {
    /**
     * DOCUMENT ME!
     *
     * @param  recipeId  id
     * @param  file
     */
    void saveImageFile(Long recipeId, MultipartFile file);
}
