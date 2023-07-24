package org.bedu.testing.persistence.entities;

import org.bedu.testing.persistence.IInterviewerRepository;
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
class InterviewerEntityTest {

    @Autowired
    private IInterviewerRepository interviewerRepository;

    @BeforeAll
    void cleanDatabases() {
        interviewerRepository.deleteAll();
    }

    @Test
    @DisplayName("Should add a new interviewer")
    void addTest() {

        InterviewerEntity interviewer = new InterviewerEntity
                                            .InterviewerEntityBuilder()
                                            .name("Francisco")
                                            .last_name("Camas")
                                            .email("francisco_Camas@hotmail.com")
                                            .is_active(true)
                                            .is_admin(true)
                                            .build();

        this.interviewerRepository.save(interviewer);
        assertNotNull(interviewer.getId());
    }

    @Test
    @DisplayName("Should remove a new added interviewer")
    void addRemove() {

        InterviewerEntity interviewer = new InterviewerEntity
                                            .InterviewerEntityBuilder()
                                            .name("Francisco")
                                            .last_name("Camas")
                                            .email("francisco_Camas@hotmail.com")
                                            .is_active(true)
                                            .is_admin(true)
                                            .build();

        this.interviewerRepository.save(interviewer);
        Long idCreated = interviewer.getId();

        this.interviewerRepository.delete(interviewer);

        Optional<InterviewerEntity> deletedInterviewer = this.interviewerRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new interviewer");
        assertTrue( deletedInterviewer.isEmpty(),"The created interviewer was not deleted");
    }

    @Test
    @DisplayName("Should remove a new added interviewer")
    void addChange() {

        String newName = "Francisco Javier";
        String newlastName = "Camas Tec.";
        String newEmail = "francisco.camas@hotmail.com";

        InterviewerEntity interviewer = new InterviewerEntity
                                            .InterviewerEntityBuilder()
                                            .name("Francisco")
                                            .last_name("Camas")
                                            .email("francisco_Camas@hotmail.com")
                                            .is_active(true)
                                            .is_admin(true)
                                            .build();

        this.interviewerRepository.save(interviewer);
        Long idCreated = interviewer.getId();

        interviewer.setName(newName);
        interviewer.setLast_name(newlastName);
        interviewer.setEmail(newEmail);

        this.interviewerRepository.save(interviewer);

        Optional<InterviewerEntity> changeInterviewer = this.interviewerRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new interviewer");
        assertFalse(changeInterviewer.isEmpty(), "There is not interviewer updated");
        assertTrue(changeInterviewer.get().getName().equals(newName), "The  interviewer's name was not updated ");
        assertTrue(changeInterviewer.get().getLast_name().equals(newlastName), "The last name of the interviewer was not updated ");
        assertTrue(changeInterviewer.get().getEmail().equals(newEmail), "The  interviewer's mail was not updated ");

    }

    @Test
    @DisplayName("Should return a new added interviewer searched by email")
    void findByEmail() {

        String NameToFind     = "Francisco Javier";
        String lastNameToFind = "Camas Tec.";
        String mailToFind     ="francisco_Camas2@hotmail.com";

        InterviewerEntity interviewer = new InterviewerEntity
                                            .InterviewerEntityBuilder()
                                            .name(NameToFind)
                                            .last_name(lastNameToFind)
                                            .email(mailToFind)
                                            .is_active(true)
                                            .build();

        this.interviewerRepository.save(interviewer);
        Long idCreated = interviewer.getId();

        Optional<InterviewerEntity> searchInterviewerByMail =  this.interviewerRepository.findByEmail(mailToFind) ;

        assertNotNull(idCreated);
        assertFalse(searchInterviewerByMail.isEmpty(), "There is not interviewer with the ["+mailToFind+"]mail");
        assertTrue(searchInterviewerByMail.get().getName().equals(NameToFind), "The  interviewer's name was not updated ");
        assertTrue(searchInterviewerByMail.get().getLast_name().equals(lastNameToFind), "The last name of the interviewer was not updated ");
        assertTrue(searchInterviewerByMail.get().getEmail().equals(mailToFind), "The  interviewer's mail was not updated ");

    }
}