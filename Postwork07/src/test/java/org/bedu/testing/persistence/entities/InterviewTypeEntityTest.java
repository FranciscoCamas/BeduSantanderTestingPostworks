package org.bedu.testing.persistence.entities;

import org.bedu.testing.persistence.IInterviewTypeRepository;
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
class InterviewTypeEntityTest {

    @Autowired
    private IInterviewTypeRepository interviewTypeRepository;

    @BeforeAll
    void cleanDatabases() {
        interviewTypeRepository.deleteAll();
    }

    @Test
    @DisplayName("Should add a new interview type")
    void addTest() {

        InterviewTypeEntity interviewType = new InterviewTypeEntity
                                            .InterviewTypeEntityBuilder()
                                            .name("Cloud Computing")
                                            .description("Make, manage and maintenance services in the cloud")
                                            .slug("Cloud")
                                            .build();

        this.interviewTypeRepository.save(interviewType);
        assertNotNull(interviewType.getId());
    }

    @Test
    @DisplayName("Should remove a new added interview type")
    void addRemove() {

        InterviewTypeEntity interviewType = new InterviewTypeEntity
                                               .InterviewTypeEntityBuilder()
                                                .name("Cloud Computing")
                                                .description("Make, manage and maintenance services in the cloud")
                                                .slug("Cloud")
                                                .build();

        this.interviewTypeRepository.save(interviewType);
        Long idCreated = interviewType.getId();

        this.interviewTypeRepository.delete(interviewType);

        Optional<InterviewTypeEntity> deletedIntType = this.interviewTypeRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new interview type");
        assertTrue( deletedIntType.isEmpty(),"The created interview type was not deleted");
    }

    @Test
    @DisplayName("Should changed properties of the new added interview type")
    void addChange() {

        String newName        = "Cloud Computing Infrastructure as a service";
        String newdescription = "Make, manage and maintenance services as a service";
        String newslug        = "Cloud AWS and Azure";

        InterviewTypeEntity interviewType = new InterviewTypeEntity
                                                .InterviewTypeEntityBuilder()
                                                .name("Cloud Computing in AWS")
                                                .description("Make, manage and mantice services with AWS")
                                                .slug("Cloud AWS")
                                                .build();

        this.interviewTypeRepository.save(interviewType);
        Long idCreated = interviewType.getId();

        interviewType.setName(newName);
        interviewType.setDescription(newdescription);
        interviewType.setSlug(newslug);

        this.interviewTypeRepository.save(interviewType);

        Optional<InterviewTypeEntity> changeIntType = this.interviewTypeRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new interview type");
        assertFalse(changeIntType.isEmpty(), "There is not interview type updated");
        assertTrue(changeIntType.get().getName().equals(newName), "The  interview type's name was not updated ");
        assertTrue(changeIntType.get().getDescription().equals(newdescription), "The description of the interview type was not updated ");
        assertTrue(changeIntType.get().getSlug().equals(newslug), "The  interview type's slug was not updated ");

    }

    @Test
    @DisplayName("Should return a new added interview type searched by slug")
    void findBySlug() {

        String nameFind        = "Cloud Computing WITH AWS and Azure";
        String descriptionFind = "Make, manage and maintenance services with AWS and Azure";
        String slugToFind      = "Cloud AWS and MS Azure as a service";

        InterviewTypeEntity interviewType = new InterviewTypeEntity
                                                .InterviewTypeEntityBuilder()
                                                .name(nameFind)
                                                .description(descriptionFind)
                                                .slug(slugToFind)
                                                .build();

        this.interviewTypeRepository.save(interviewType);
        Long idCreated = interviewType.getId();

        Optional<InterviewTypeEntity> searchCandidateBySlug =  this.interviewTypeRepository.findBySlug (slugToFind) ;

        assertNotNull(idCreated);
        assertFalse(searchCandidateBySlug.isEmpty(), "There is not interview type with the ["+slugToFind+"] slug");
        assertTrue(searchCandidateBySlug.get().getName().equals(nameFind), "The  interview type's name was not updated ");
        assertTrue(searchCandidateBySlug.get().getDescription().equals(descriptionFind), "The description of the interview type was not updated ");
        assertTrue(searchCandidateBySlug.get().getSlug().equals(slugToFind), "The  interview type's slug was not updated ");

    }

}