package com.tony.recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity public class Category {
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
