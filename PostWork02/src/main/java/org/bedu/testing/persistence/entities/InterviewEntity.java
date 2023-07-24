package org.bedu.testing.persistence.entities;

import lombok.*;
import javax.persistence.*;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

@Getter
@Setter
@AllArgsConstructor
@Table(name = "INTERVIEW")
@javax.persistence.Entity
@NoArgsConstructor
@Builder
public class InterviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;

    @Column( nullable = false)
    private String              name;

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private CandidateEntity     candidate;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", referencedColumnName = "id")
    private InterviewerEntity   interviewer;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private InterviewTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    private  TechnologyEntity   technology;

    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private DisciplineEntity    discipline;
}