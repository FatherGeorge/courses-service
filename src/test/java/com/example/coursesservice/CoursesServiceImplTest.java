package com.example.coursesservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public final class CoursesServiceImplTest {

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    CoursesService coursesService;

    @Before
    public void beforeEach() {
        coursesRepository.deleteAll();
    }

    @After
    public void afterEach() {
        coursesRepository.deleteAll();
    }

    @Test
    public void listCoursesReturnsEmptyList() {
        // Setup

        // Execute
        Iterable<Course> actual = coursesService.listCourses();

        // Assert
        assertThat(actual, is(Collections.EMPTY_LIST));
    }

    @Test
    public void listCoursesReturnsList() {
        // Setup
        Course expected = new Course("react");
        coursesRepository.save(expected);

        // Execute
        Iterable<Course> actual = coursesService.listCourses();

        // Assert
        assertThat(actual.iterator().next(), is(expected));
    }

    @Test
    public void findCourseByNameReturnsEmptyOptional() {
        // Setup
        Course expected = new Course("react");
        coursesRepository.save(expected);

        // Execute
        Optional<Course> actual = coursesService.findCourseByName("java");

        // Assert
        assertFalse(actual.isPresent());
    }

    @Test
    public void findCourseByNameReturnsCourse() {
        // Setup
        Course expected = new Course("react");
        coursesRepository.save(expected);

        // Execute
        Optional<Course> actual = coursesService.findCourseByName("react");

        // Assert
        assertThat(actual.get(), is(expected));
    }

    @Test
    public void saveCourseSavesCourse() {
        // Setup
        Course expected = new Course("react");
        coursesService.saveCourse(expected);

        // Execute
        Optional<Course> actual = coursesService.findCourseByName("react");

        Iterable<Course> listCourses = coursesRepository.findAll();

        // Assert
        assertThat(actual.get(), is(expected));
        assertThat(listCourses.spliterator().getExactSizeIfKnown(), is(1L));
    }

    @Test
    public void deleteCourse() {
        // Setup
        Course expected = new Course("react");
        coursesRepository.save(expected);

        // Execute
        coursesService.deleteCourseByName("react");

        Iterable<Course> listCourses = coursesRepository.findAll();

        // Assert
        assertThat(listCourses.spliterator().getExactSizeIfKnown(), is(0L));
    }
}
