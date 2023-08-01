package org.bedu.testing.persistence.entities;

import org.bedu.testing.persistence.ITechnologyRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.testing")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TechnologyEntityTest {

    @Autowired
    private ITechnologyRepository technologyRepository;

    @BeforeAll
    void cleanDatabases() {
        technologyRepository.deleteAll();
    }

    @Test
    @DisplayName("Should add a new technology")
    void addTest() {

        TechnologyEntity technology = new TechnologyEntity
                .TechnologyEntityBuilder()
                .name("AWS Services")
                .description("Make, manage and maintenance services with AWS")
                .slug("Cloud AWS")
                .build();

        this.technologyRepository.save(technology);
        assertNotNull(technology.getId());
    }

    @Test
    @DisplayName("Should remove a new added technology")
    void addRemove() {

        TechnologyEntity technology = new TechnologyEntity
                .TechnologyEntityBuilder()
                .name("AWS Services")
                .description("Make, manage and maintenance services with AWS and Google")
                .slug("Cloud AWS")
                .build();

        this.technologyRepository.save(technology);
        Long idCreated = technology.getId();

        this.technologyRepository.delete(technology);

        Optional<TechnologyEntity> deletedTech = this.technologyRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new technology");
        assertTrue( deletedTech.isEmpty(),"The created technology was not deleted");
    }

    @Test
    @DisplayName("Should changed properties of the new added technology")
    void addChange() {

        String newName        = "Cloud Computing WITH AWS and Azure";
        String newdescription = "Make, manage and mantice services with AWS, Google and Azure";
        String newslug        = "Cloud AWS and Azure";

        TechnologyEntity technology = new TechnologyEntity
                .TechnologyEntityBuilder()
                .name("Cloud Computing in AWS")
                .description("Make, manage and mantice services with AWS")
                .slug("Cloud AWS")
                .build();

        this.technologyRepository.save(technology);
        Long idCreated = technology.getId();

        technology.setName(newName);
        technology.setDescription(newdescription);
        technology.setSlug(newslug);

        this.technologyRepository.save(technology);

        Optional<TechnologyEntity> changeTech = this.technologyRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new technology");
        assertFalse(changeTech.isEmpty(), "There is not technology updated");
        assertTrue(changeTech.get().getName().equals(newName), "The  technology's name was not updated ");
        assertTrue(changeTech.get().getDescription().equals(newdescription), "The description of the technology was not updated ");
        assertTrue(changeTech.get().getSlug().equals(newslug), "The  technology's slug was not updated ");

    }

    @Test
    @DisplayName("Should return a new added discipline searched by email")
    void findBySlug() {

        String nameFind        = "Cloud Computing WITH AWS and Azure";
        String descriptionFind = "Make, manage and maintenance services with AWS and Azure";
        String slugToFind      = "Cloud AWS and MS Azure ";

        TechnologyEntity technology = new TechnologyEntity
                .TechnologyEntityBuilder()
                .name(nameFind)
                .description(descriptionFind)
                .slug(slugToFind)
                .build();

        this.technologyRepository.save(technology);
        Long idCreated = technology.getId();

        Optional<TechnologyEntity> searchTechBySlug =  this.technologyRepository.findBySlug (slugToFind) ;

        assertNotNull(idCreated);
        assertFalse(searchTechBySlug.isEmpty(), "There is not technology with the ["+slugToFind+"]mail");
        assertTrue(searchTechBySlug.get().getName().equals(nameFind), "The  technology's name was not updated ");
        assertTrue(searchTechBySlug.get().getDescription().equals(descriptionFind), "The description of the technology was not updated ");
        assertTrue(searchTechBySlug.get().getSlug().equals(slugToFind), "The  technology's slug was not updated ");

    }

}