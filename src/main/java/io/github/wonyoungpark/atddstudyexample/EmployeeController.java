package io.github.wonyoungpark.atddstudyexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wyparks2@gmail.com on 2018. 7. 12.
 * Blog : http://WonYoungPark.github.io
 * Github : http://github.com/WonYoungPark
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("/hello/{lastName}")
    public String greeting(@PathVariable String lastName) {
        return greetingService.greet(lastName);
    }
}
