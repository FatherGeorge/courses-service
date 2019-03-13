package com.example.coursesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoursesController {

    @Autowired
    CoursesService coursesService;

    @GetMapping("/appInfo")
    String smokeTest() {
        return "App is running";
    }

    @GetMapping("/")
    Iterable<Course> listCourses() {
        return coursesService.listCourses();
    }
}
