package com.example.coursesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CoursesController {

    @Autowired
    CoursesService coursesService;

    @GetMapping("/appInfo")
    String smokeTest() {
        return "App is running";
    }

    @GetMapping("/")
    Response listCourses() {
        Iterable<Course> courses = coursesService.listCourses();
        Response response = new Response();
        response.setStatusCode("0");
        response.setStatusDesc("Ok");
        List<Course> listCourses = new ArrayList<>();
        courses.forEach(listCourses::add);
        response.setCourses(listCourses);

        return response;
    }

/*    @PostMapping("/course")
    Course addCourse(@RequestBody Optional<Course> courseOptional) {

    }*/
}
