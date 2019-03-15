package com.example.coursesservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
    Optional<Course> findCourseByName(String name);
    
    @Transactional
    void deleteCoursesByName(String name);
}
