package org.bedu.testing.persistence.entities;

import org.bedu.testing.persistence.IDisciplineRepository;
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
class DisciplineEntityTest {

    @Autowired
    private IDisciplineRepository disciplineRepository;

    @BeforeAll
    void cleanDatabases() {
        disciplineRepository.deleteAll();
    }

    @Test
    @DisplayName("Should add a new discipline")
    void addTest() {

        DisciplineEntity discipline = new DisciplineEntity
                                     .DisciplineEntityBuilder()
                                     .name("Cloud Computing in AWS")
                                     .description("Make, manage and maintenance services with AWS")
                                     .slug("Cloud AWS")
                                     .build();

        this.disciplineRepository.save(discipline);
        assertNotNull(discipline.getId());
    }

    @Test
    @DisplayName("Should remove a new added discipline")
    void addRemove() {

        DisciplineEntity discipline = new DisciplineEntity
                                     .DisciplineEntityBuilder()
                                     .name("Cloud Computing in AWS")
                                     .description("Make, manage and maintenance services with AWS")
                                     .slug("Cloud AWS")
                                     .build();

        this.disciplineRepository.save(discipline);
        Long idCreated = discipline.getId();

        this.disciplineRepository.delete(discipline);

        Optional<DisciplineEntity> deletedDisp = this.disciplineRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new discipline");
        assertTrue( deletedDisp.isEmpty(),"The created discipline was not deleted");
    }

    @Test
    @DisplayName("Should changed properties of the new added discipline")
    void addChange() {

        String newName        = "Cloud Computing WITH AWS and Azure";
        String newdescription = "Make, manage and mantice services with AWS and Azure";
        String newslug        = "Cloud AWS and Azure";

        DisciplineEntity discipline = new DisciplineEntity
                                         .DisciplineEntityBuilder()
                                         .name("Cloud Computing in AWS")
                                         .description("Make, manage and mantice services with AWS")
                                         .slug("Cloud AWS")
                                         .build();

        this.disciplineRepository.save(discipline);
        Long idCreated = discipline.getId();

        discipline.setName(newName);
        discipline.setDescription(newdescription);
        discipline.setSlug(newslug);

        this.disciplineRepository.save(discipline);

        Optional<DisciplineEntity> changeEnt = this.disciplineRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new discipline");
        assertFalse(changeEnt.isEmpty(), "There is not discipline updated");
        assertTrue(changeEnt.get().getName().equals(newName), "The  discipline's name was not updated ");
        assertTrue(changeEnt.get().getDescription().equals(newdescription), "The name of the discipline was not updated ");
        assertTrue(changeEnt.get().getSlug().equals(newslug), "The  discipline's slug was not updated ");

    }

    @Test
    @DisplayName("Should return a new added discipline searched by email")
    void findBySlug() {

        String nameFind        = "Cloud Computing WITH AWS and Azure";
        String descriptionFind = "Make, manage and maintenance services with AWS and Azure";
        String slugToFind      = "Cloud AWS and MS Azure ";

        DisciplineEntity discipline = new DisciplineEntity
                .DisciplineEntityBuilder()
                .name(nameFind)
                .description(descriptionFind)
                .slug(slugToFind)
                .build();

        this.disciplineRepository.save(discipline);
        Long idCreated = discipline.getId();

        Optional<DisciplineEntity> searchCandidateBySlug =  this.disciplineRepository.findBySlug (slugToFind) ;

        assertNotNull(idCreated);
        assertFalse(searchCandidateBySlug.isEmpty(), "There is not discipline with the ["+slugToFind+"]mail");
        assertTrue(searchCandidateBySlug.get().getName().equals(nameFind), "The  discipline's name was not updated ");
        assertTrue(searchCandidateBySlug.get().getDescription().equals(descriptionFind), "The description of the discipline was not updated ");
        assertTrue(searchCandidateBySlug.get().getSlug().equals(slugToFind), "The  discipline's slug was not updated ");

    }

}