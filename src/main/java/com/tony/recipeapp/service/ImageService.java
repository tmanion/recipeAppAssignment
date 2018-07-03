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
     * @param  id
     * @param  file
     */
    void saveImageFile(Long id, MultipartFile file);
}
