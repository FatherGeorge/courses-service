package com.example.coursesservice;

import java.util.Optional;

public interface CoursesService {
    Iterable<Course> listCourses();

    Optional<Course> findCourseByName(String name);

    Course saveCourse(Course courseToAdd);

    void deleteCourseByName(String name);
}
