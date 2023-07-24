package stepdefinitions;

import io.cucumber.spring.CucumberContextConfiguration;
import org.bedu.testing.Main;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Main.class }, loader = SpringBootContextLoader.class)
@SpringBootTest
@CucumberContextConfiguration
public class BaseSteps {

}
