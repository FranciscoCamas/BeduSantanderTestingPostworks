package org.bedu.testing.persistence;

import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Repository
public interface IInterviewerRepository extends JpaRepository<InterviewerEntity,Long> {
    public Optional<InterviewerEntity> findByEmail(String mail);
}
