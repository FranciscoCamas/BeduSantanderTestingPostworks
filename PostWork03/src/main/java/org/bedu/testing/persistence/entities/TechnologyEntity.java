package org.bedu.testing.persistence.entities;

import lombok.*;
import javax.persistence.*;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@Table( name = "TECHNOLOGY" )
@javax.persistence.Entity
@NoArgsConstructor
@Builder
public class TechnologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @Column( nullable = false)
    private String name;

    @Column( nullable = false)
    private String slug;

    @Column( nullable = false)
    private String description;
}