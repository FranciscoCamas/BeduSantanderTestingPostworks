package org.bedu.testing.persistence;

import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */
@Repository
@Component
public interface IInterviewerRepository extends JpaRepository<InterviewerEntity,Long> {
    public Optional<InterviewerEntity> findByEmail(String mail);

    List< InterviewerEntity>  findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrLastNameContainingIgnoreCase(String sname,String sEmail,String sLastname);
}
