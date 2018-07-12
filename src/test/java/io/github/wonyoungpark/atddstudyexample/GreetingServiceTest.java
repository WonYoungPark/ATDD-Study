package io.github.wonyoungpark.atddstudyexample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;

/**
 * Created by wyparks2@gmail.com on 2018. 7. 12.
 * Blog : http://WonYoungPark.github.io
 * Github : http://github.com/WonYoungPark
 */
@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceTest {
    private GreetingService greetService;
    @Mock private EmployeeRepository repository;
    private final String nonExistingLastName = "nonExistingLastName";
    private final String existingLastName = "existingLastName";
    private final String firstName = "firstName";
    private final String lastName = "lastName";

    @Before
    public void setUp() throws Exception {
        greetService = new GreetingService();
        greetService.repository = repository;
        given(repository.findByLastName(nonExistingLastName))
            .willReturn(Optional.empty());
        given(repository.findByLastName(existingLastName))
            .willReturn(Optional.of(new Employee(lastName, firstName)));
    }

    @Test
    public void greet_with_nonExisting_last_name_should_return_default_message() {
        String msg = greetService.greet(nonExistingLastName);
        Assert.assertEquals(msg, "Who is this " + nonExistingLastName + " you're talking about?");
    }

    @Test
    public void greet_with_existing_last_name_should_return_hello_message_with_appropriate_names() {
        String msg1 = greetService.greet(existingLastName);
        Assert.assertEquals(msg1, String.format("Hello %s %s!", firstName, lastName));
    }
}
