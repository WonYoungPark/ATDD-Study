package io.github.wonyoungpark.atddstudyexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by wyparks2@gmail.com on 2018. 7. 12.
 * Blog : http://WonYoungPark.github.io
 * Github : http://github.com/WonYoungPark
 */
@Service
public class GreetingService {
    @Autowired
    EmployeeRepository repository;

    public String greet(String lastName) {
        Optional<Employee> employee = repository.findByLastName(lastName);
        return employee.map(e -> String.format("Hello %s %s!", e.getFirstName(), e.getLastName()))
                       .orElse("Who is this " + lastName + " you're talking about?");
    }
}
