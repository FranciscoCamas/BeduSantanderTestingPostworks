package org.bedu.testing.persistence;

import org.bedu.testing.persistence.entities.InterviewTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Repository
public interface IInterviewTypeRepository extends JpaRepository<InterviewTypeEntity,Long> {
    public Optional<InterviewTypeEntity> findBySlug(String Slug);
    List<InterviewTypeEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSlugContainingIgnoreCase(String name, String description, String slug);

}