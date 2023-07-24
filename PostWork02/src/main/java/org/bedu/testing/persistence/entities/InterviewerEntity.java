package org.bedu.testing.persistence.entities;

import lombok.*;

import javax.persistence.*;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

@Getter
@Setter
@AllArgsConstructor
@Table(name = "INTERVIEWER" )
@javax.persistence.Entity
@NoArgsConstructor
@Builder
public class InterviewerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @Column( nullable = false)
    private String name;

    @Column( nullable = false)
    private String last_name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column( nullable = false)
    private boolean is_active;

    @Column( nullable = false)
    private boolean is_admin;
}