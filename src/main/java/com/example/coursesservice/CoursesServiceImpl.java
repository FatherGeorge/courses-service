package com.example.coursesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public Iterable<Course> listCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Optional<Course> findCourseByName(String name) {
        return coursesRepository.findCourseByName(name);
    }

    @Override
    public Course saveCourse(Course courseToAdd) {
        return coursesRepository.save(courseToAdd);
    }

    @Override
    public void deleteCourseByName(String name) {
        coursesRepository.deleteCoursesByName(name);
    }
}
