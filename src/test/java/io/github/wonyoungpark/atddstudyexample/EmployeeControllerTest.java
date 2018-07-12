package io.github.wonyoungpark.atddstudyexample;

import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * Created by wyparks2@gmail.com on 2018. 7. 12.
 * Blog : http://WonYoungPark.github.io
 * Github : http://github.com/WonYoungPark
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    classes = Application.class
)
@TestPropertySource(
    locations = "classpath:application.properties"
)
public class EmployeeControllerTest {
    @MockBean
    private EmployeeRepository repository;

    private RequestSpecification basicRequest;

    @Before
    public void setUp() {
        basicRequest = given()
            .baseUri("http://localhost")
            .port(8080);

//        repository.deleteAll();
        repository.save(new Employee("Park", "Won Young"));
    }

    @Test
    public void shouldReturnDefaultMessageWhenLastNameNotFound() {
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        given().spec(basicRequest).basePath("/api/hello/" + nonExistingLastName)
               .when().get()
               .then().log().body()
               .statusCode(HttpStatus.OK.value())
               .body(is(expectedMessage));
    }

    @Test
    public void shouldReturnGreetingMessageWhenLastNameFound() {
        String existingLastName = "Park";
        String expectedMessage = "Hello Won Young Park!";

        given().spec(basicRequest).basePath("/api/hello/" + existingLastName)
               .when().get()
               .then().log().body()
               .statusCode(HttpStatus.OK.value())
               .body(is(expectedMessage));
    }
}
