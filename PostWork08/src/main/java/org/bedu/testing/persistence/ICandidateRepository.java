package org.bedu.testing.persistence;

import org.bedu.testing.persistence.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

@Repository
public interface ICandidateRepository extends JpaRepository<CandidateEntity,Long>{

    public Optional<CandidateEntity> findByEmail(String mail);

    List<CandidateEntity> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrLastNameContainingIgnoreCase(String sname, String sEmail, String sLastname);
}
