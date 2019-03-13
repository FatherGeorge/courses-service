package com.example.coursesservice;

import java.util.List;
import java.util.Optional;

public interface CoursesService {
    Iterable<Course> listCourses();
    Optional<Course> findCourseByName(String name);
}
