package com.example.coursesservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
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
    public void  listCoursesReturnsEmptyList() {
        // Setup

        // Execute
        Iterable<Course> actual = coursesService.listCourses();

        // Assert
        assertThat(actual, is(Collections.EMPTY_LIST));
    }

    @Test
    public void  listCoursesReturnsList() {
        // Setup
        Course expected = new Course("react");
        coursesRepository.save(expected);

        // Execute
        Iterable<Course> actual = coursesService.listCourses();

        // Assert
        assertThat(actual.iterator().next(), is(expected));
    }


}
