package org.bedu.testing.persistence;

import org.bedu.testing.persistence.entities.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Repository
public interface IInterviewRepository extends JpaRepository<InterviewEntity,Long> {
    public Optional<InterviewEntity> findByName(String nameFind);
}
