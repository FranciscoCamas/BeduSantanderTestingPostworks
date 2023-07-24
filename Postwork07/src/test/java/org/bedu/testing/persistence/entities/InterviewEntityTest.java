package org.bedu.testing.persistence.entities;

import org.bedu.testing.persistence.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.testing")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InterviewEntityTest {

    private CandidateEntity candidate;
    private InterviewerEntity interviewer;

    private InterviewTypeEntity interviewType;

    private TechnologyEntity technology;

    private DisciplineEntity discipline;

    @Autowired
    private IInterviewRepository interviewRepository;

    @Autowired
    private ICandidateRepository candidateRepository ;

    @Autowired
    private IInterviewerRepository interviewerRepository;

    @Autowired
    private IInterviewTypeRepository interviewTypeRepository;

    @Autowired
    private ITechnologyRepository technologyRepository;

    @Autowired
    private IDisciplineRepository disciplineRepository;

    @BeforeAll
    @DisplayName("Initializing the data ...")
    void smokeTest(){

        this.candidateRepository.deleteAll();
        this.interviewerRepository.deleteAll();
        this.interviewTypeRepository.deleteAll();
        this.technologyRepository.deleteAll();
        this.disciplineRepository.deleteAll();

        /* doom to this table
        * What happen if there are two values with the same name ?*/
        this.interviewRepository.deleteAllInBatch();

        candidate     = getCandidate("Francisco","Camas","francisco_Camas@hotmail.com",true);

        interviewer   = getInterviewer("Francisco","Camas","francisco_Camas@hotmail.com",true,true);

        interviewType = getInterviewType("Cloud Computing","Make, manage and maintenance services in the cloud","Cloud");

        technology    = getTechnologyEntity("AWS Services","Make, manage and maintenance services with AWS","Cloud AWS");

        discipline    = getDisciplineEntity("Cloud Computing in AWS","Make, manage and maintenance services with AWS","Cloud AWS");

        assumeTrue( candidate     != null);
        assumeTrue( interviewer   != null);
        assumeTrue( interviewType != null);
        assumeTrue( technology    != null);
        assumeTrue( discipline    != null);

    }

    @Test
    @DisplayName("Should add a new interview")
    void addTest() {

        InterviewEntity interview = new InterviewEntity
                                    .InterviewEntityBuilder()
                .name("First interview")
                .candidate(candidate)
                .interviewer(interviewer)
                .type(interviewType)
                .technology(technology)
                .discipline(discipline)
                .build();

        this.interviewRepository.save(interview);
        assertNotNull(interview.getId());

    }

    @Test
    @DisplayName("Should remove a new added interview")
    void addRemove() {

        InterviewEntity interview = new InterviewEntity
                .InterviewEntityBuilder()
                .name(" Second interview")
                .candidate(candidate)
                .interviewer(interviewer)
                .type(interviewType)
                .technology(technology)
                .discipline(discipline)
                .build();

        this.interviewRepository.save(interview);
        Long idCreated = interview.getId();

        assumeTrue( interview  != null);

        this.interviewRepository.delete(interview);

        Optional<InterviewEntity> deletedInterview = this.interviewRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new interview");
        assertTrue( deletedInterview.isEmpty(),"The created interview was not deleted");
    }

    @Test
    @DisplayName("Should changed properties of the new added discipline")
    void addChange() {

        String newName        = "Cloud Computing WITH AWS and Azure";

        InterviewEntity interview = new InterviewEntity
                .InterviewEntityBuilder()
                .name("Third interview")
                .candidate(candidate)
                .interviewer(interviewer)
                .type(interviewType)
                .technology(technology)
                .discipline(discipline)
                .build();

        this.interviewRepository.save(interview);
        Long idCreated = interview.getId();

        assumeTrue( interview  != null);

        CandidateEntity     newCandidate     = getCandidate("Javier","Tec","francisco_Camas@hotmail.com",true);

        InterviewerEntity   newInterviewer   = getInterviewer("Xavier","Uitz","javier_uitz@hotmail.com",true,true);

        InterviewTypeEntity newInterviewType = getInterviewType("Proxy enhance","Make, manage and maintenance proxies services","Infrastructure");

        TechnologyEntity    newTechnology    = getTechnologyEntity("MicroServices","Make, manage and maintenance  MicroServices","Cloud");

        DisciplineEntity    newDiscipline    = getDisciplineEntity("Services as Infrastructure","Make, manage and maintenance services with AWS","Cloud AWS");

        assumeTrue( newCandidate     != null);
        assumeTrue( newInterviewer   != null);
        assumeTrue( newInterviewType != null);
        assumeTrue( newTechnology    != null);
        assumeTrue( newDiscipline    != null);

        interview.setName("Four interview");

        interview.setCandidate(newCandidate);
        interview.setInterviewer(newInterviewer);
        interview.setType(newInterviewType);
        interview.setTechnology(newTechnology);
        interview.setDiscipline(newDiscipline);

        this.interviewRepository.save(interview);

        Optional<InterviewEntity> changeEnt = this.interviewRepository.findById(idCreated);

        assertNotNull(idCreated,"It wasn't possible create the new interview");
        assertFalse(changeEnt.isEmpty(), "There is not interview updated");
        assertTrue(changeEnt.get().getName().equals("Four interview"), "The  interview new name was not updated ");

        assertTrue(changeEnt.get().getCandidate().getName().equals("Javier"), "The name of the candidate was not updated ");
        assertTrue(changeEnt.get().getCandidate().getLast_name().equals("Tec"), "The  candidate's last name was not updated ");
        assertTrue(changeEnt.get().getCandidate().getEmail().equals("francisco_Camas@hotmail.com"), "The  candidate's email was not updated ");

        assertTrue(changeEnt.get().getInterviewer().getName().equals("Xavier"), "The name of the Interviewer was not updated ");
        assertTrue(changeEnt.get().getInterviewer().getLast_name().equals("Uitz"), "The  Interviewer's last name was not updated ");
        assertTrue(changeEnt.get().getInterviewer().getEmail().equals("javier_uitz@hotmail.com"), "The  Interviewer's email was not updated ");

        assertTrue(changeEnt.get().getType().getName().equals("Proxy enhance"), "The name of the Interview Type was not updated ");
        assertTrue(changeEnt.get().getType().getDescription(). equals("Make, manage and maintenance proxies services"), "The  Interview Type description was not updated ");
        assertTrue(changeEnt.get().getType().getSlug() .equals("Infrastructure"), "The slug of the Interview Type  was not updated ");

        assertTrue(changeEnt.get().getTechnology().getName().equals("MicroServices"), "The name of the Technology  was not updated ");
        assertTrue(changeEnt.get().getTechnology().getDescription(). equals("Make, manage and maintenance  MicroServices"), "The  Technology description was not updated ");
        assertTrue(changeEnt.get().getTechnology().getSlug() .equals("Cloud"), "The slug of the Technology  was not updated ");

        assertTrue(changeEnt.get().getDiscipline().getName().equals("Services as Infrastructure"), "The name of the Discipline  was not updated ");
        assertTrue(changeEnt.get().getDiscipline().getDescription(). equals("Make, manage and maintenance services with AWS"), "The  Discipline description was not updated ");
        assertTrue(changeEnt.get().getDiscipline().getSlug() .equals("Cloud AWS"), "The slug of the Discipline  was not updated ");

    }

    @Test
    @DisplayName("Should return a new added interview searched by name")
    void findByName() {

        String NameToFind     = "Five interview";

        InterviewEntity interview = new InterviewEntity
                .InterviewEntityBuilder()
                .name(NameToFind)
                .candidate(candidate)
                .interviewer(interviewer)
                .type(interviewType)
                .technology(technology)
                .discipline(discipline)
                .build();

        this.interviewRepository.save(interview);
        Long idCreated = interview.getId();

        assumeTrue( interview  != null);

        Optional<InterviewEntity> searchInterviewerByMail =  this.interviewRepository.findByName(NameToFind) ;

        assertNotNull(idCreated);
        assertFalse(searchInterviewerByMail.isEmpty(), "There is not interview with the name ["+NameToFind+"]");
        assertTrue(searchInterviewerByMail.get().getName().equals(NameToFind), "The  interview with the name was not found ");

    }
    private CandidateEntity getCandidate(String sName,String slastName,String sEmail,Boolean isActive){

        candidate = new CandidateEntity
                .CandidateEntityBuilder()
                .name(sName)
                .last_name(slastName)
                .email(sEmail)
                .is_active(isActive)
                .build();

        this.candidateRepository.save(candidate);

        return candidate;
    }

    private InterviewerEntity getInterviewer(String sName,String slastName,String sEmail,Boolean isActive,Boolean isAdmin) {

        InterviewerEntity interviewer = new InterviewerEntity
                .InterviewerEntityBuilder()
                .name(sName)
                .last_name(slastName)
                .email(sEmail)
                .is_active(isActive)
                .is_admin(isAdmin)
                .build();

        this.interviewerRepository.save(interviewer);

        return interviewer;
    }

    private InterviewTypeEntity getInterviewType(String sName,String sDescription,String slug){

        InterviewTypeEntity interviewType = new InterviewTypeEntity
                                                .InterviewTypeEntityBuilder()
                                                .name(sName)
                                                .description(sDescription)
                                                .slug(slug)
                                                .build();

       this.interviewTypeRepository.save(interviewType);

       return interviewType;
    }

    private TechnologyEntity getTechnologyEntity(String sName,String sDescription,String slug) {
        TechnologyEntity technology = new TechnologyEntity
                                            .TechnologyEntityBuilder()
                                            .name(sName)
                                            .description(sDescription)
                                            .slug(slug)
                                            .build();

        this.technologyRepository.save(technology);

        return technology;
    }

    private DisciplineEntity getDisciplineEntity(String sName,String sDescription,String slug) {

        DisciplineEntity  discipline = new DisciplineEntity
                                            .DisciplineEntityBuilder()
                                            .name(sName)
                                            .description(sDescription)
                                            .slug(slug)
                                            .build();

        this.disciplineRepository.save(discipline);

        return discipline;
    }
}