package com.example.coursesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    Response addCourse(@RequestBody Course courseToAdd) {
        final Optional<Course> course = coursesService.findCourseByName(courseToAdd.getName());
        if (course.isPresent())
            return new Response().setStatusCode("2").setStatusDesc("Course already exists");

        Course savedCourse = coursesService.saveCourse(courseToAdd);
        return new Response().setStatusCode("0").setStatusDesc("Ok").setCourse(savedCourse);
    }

    @DeleteMapping("/")
    Response deketeCourse(@RequestBody Course courseToDelete) {
        final Optional<Course> course = coursesService.findCourseByName(courseToDelete.getName());
        if (!course.isPresent())
            return new Response().setStatusCode("3").setStatusDesc("Course doesn't exist");

        coursesService.deleteCourseByName(courseToDelete.getName());

        return new Response().setStatusCode("0").setStatusDesc("Ok");
    }

    @PutMapping("/")
    Response changeCourseName(@RequestBody Course courseToChange) {
        final Optional<Course> course = coursesService.findCourseByName(courseToChange.getName());
        if (!course.isPresent())
            return new Response().setStatusCode("3").setStatusDesc("Course doesn't exist");

        coursesService.saveCourse(courseToChange);

        return new Response().setStatusCode("0").setStatusDesc("Ok");
    }
}
