package org.bedu.testing.persistence.entities;

import org.bedu.testing.persistence.ICandidateRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.testing")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CandidateEntityTest {

    @Autowired
    private ICandidateRepository candidateRepository ;

    @BeforeAll
    void cleanDatabases() {
        candidateRepository.deleteAll();
    }

    @Test
    @DisplayName("Should add a new candidate")
    void addTest() {

        CandidateEntity candidate = new CandidateEntity
                                       .CandidateEntityBuilder()
                                       .name("Francisco")
                                       .last_name("Camas")
                                       .email("francisco_Camas@hotmail.com")
                                       .is_active(true)
                                       .build();

        this.candidateRepository.save(candidate);
        assertNotNull(candidate.getId());
    }

    @Test
    @DisplayName("Should remove a new added candidate")
    void addRemove() {

        CandidateEntity candidate = new CandidateEntity
                                       .CandidateEntityBuilder()
                                       .name("Francisco")
                                       .last_name("Camas")
                                       .email("francisco_Camas@hotmail.com")
                                       .is_active(true)
                                       .build();

        this.candidateRepository.save(candidate);
        Long idCreated = candidate.getId();

        this.candidateRepository.delete(candidate);

        Optional<CandidateEntity> deletedCand = this.candidateRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new candidate");
        assertTrue( deletedCand.isEmpty(),"The created candidate was not deleted");
    }

    @Test
    @DisplayName("Should remove a new added candidate")
    void addChange() {

        String newName = "Francisco Javier";
        String newlastName = "Camas Tec.";
        String newEmail = "francisco.camas@hotmail.com";

        CandidateEntity candidate = new CandidateEntity
                                       .CandidateEntityBuilder()
                                       .name("Francisco")
                                       .last_name("Camas")
                                       .email("francisco_Camas@hotmail.com")
                                       .is_active(true)
                                       .build();

        this.candidateRepository.save(candidate);
        Long idCreated = candidate.getId();

        candidate.setName(newName);
        candidate.setLast_name(newlastName);
        candidate.setEmail(newEmail);

        this.candidateRepository.save(candidate);

        Optional<CandidateEntity> changeCand = this.candidateRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new candidate");
        assertFalse(changeCand.isEmpty(), "There is not candidate updated");
        assertTrue(changeCand.get().getName().equals(newName), "The  candidate's name was not updated ");
        assertTrue(changeCand.get().getLast_name().equals(newlastName), "The last name of the candidate was not updated ");
        assertTrue(changeCand.get().getEmail().equals(newEmail), "The  candidate's mail was not updated ");

    }

    @Test
    @DisplayName("Should return a new added candidate searched by email")
    void findByEmail() {

        String NameToFind     = "Francisco Javier";
        String lastNameToFind = "Camas Tec.";
        String mailToFind     ="francisco_Camas2@hotmail.com";

        CandidateEntity candidate = new CandidateEntity
                                        .CandidateEntityBuilder()
                                        .name(NameToFind)
                                        .last_name(lastNameToFind)
                                        .email(mailToFind)
                                        .is_active(true)
                                        .build();

        this.candidateRepository.save(candidate);
        Long idCreated = candidate.getId();

        Optional<CandidateEntity> searchCandidateByMail =  this.candidateRepository.findByEmail(mailToFind) ;

        assertNotNull(idCreated);
        assertFalse(searchCandidateByMail.isEmpty(), "There is not candidate with the ["+mailToFind+"]mail");
        assertTrue(searchCandidateByMail.get().getName().equals(NameToFind), "The  candidate's name was not updated ");
        assertTrue(searchCandidateByMail.get().getLast_name().equals(lastNameToFind), "The last name of the candidate was not updated ");
        assertTrue(searchCandidateByMail.get().getEmail().equals(mailToFind), "The  candidate's mail was not updated ");
        
    }
}