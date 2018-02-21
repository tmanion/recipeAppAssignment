package com.tony.recipeapp.domain;

import lombok.Data;

import javax.persistence.*;


/**
 * DOCUMENT ME!
 *
 * @version  $Revision$, $Date$
 */
@Data
@Entity public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne private Recipe recipe;
    @Lob private String recipeNotes;
}
