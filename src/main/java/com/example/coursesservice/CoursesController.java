package com.example.coursesservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoursesController {

    @GetMapping("/appInfo")
    String smokeTest() {
        return "App is running";
    }
}
