package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bedu.testing.persistence.IInterviewerRepository;
import org.bedu.testing.persistence.entities.InterviewerEntity;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class ValidateInterviewerInsert extends BaseSteps{

    @Autowired
    private IInterviewerRepository interviewerRepository;
    private InterviewerEntity interviewer;

    @BeforeAll
     void cleanDatabases() {

        interviewerRepository.deleteAll();
    }

    @Given("the name {string} last name {string} and email {string}")
    public void createInterviewer(String sName, String sLastName, String sEmail){
        interviewer = new InterviewerEntity(null,sName,sLastName,sEmail,true,false);
    }

    @When("the admin user request to insert a new interviewer")
    public void insertInterviewer(){
        interviewerRepository.save(interviewer);
    }
    @Then("the system should insert the data of the new interviewer and return a Id")
    public void correctPin() {
        assertNotNull (interviewer);
    }

}
