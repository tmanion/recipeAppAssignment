package com.tony.recipeapp.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Controller public class IndexController {
    /**
     * Creates a new IndexController object.
     */
    public IndexController() {
    }

    /**
     * Returns the index page value.
     *
     * @return  index page value.
     */
    @RequestMapping({ "", "/", "/index" })
    public String getIndexPage() {
        return "index";
    }
}
