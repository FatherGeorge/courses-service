package com.example.coursesservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public final class CoursesControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Mock
    private CoursesService coursesService;

    @InjectMocks
    private CoursesController coursesController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(coursesController).build();
    }

    @Test
    public void smokeTest() throws Exception {
        // Execute
        String actual = mockMvc.perform(get("/appInfo"))
                .andExpect(status()
                        .isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Assert
        assertThat(actual, is("App is running"));
    }

    @Test
    public void getReturnsListOfCoursesStatus0() throws Exception {
        // Setup
        Course course = new Course("springBoot", "mandatory course for java engineer");
        List<Course> courses = new ArrayList<>();
        courses.add(course);

        when(coursesService.listCourses()).thenReturn(courses);
        // Execute
        String actualString = mockMvc.perform(get("/"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Response actual = OBJECT_MAPPER.readValue(actualString, Response.class);

        // Assert
        assertThat(actual.getCourses().get(0), is(course));
        assertThat(actual.getStatusCode(), is("0"));
        assertThat(actual.getStatusDesc(), is("Ok"));
    }

    @Test
    public void addCourseReturnsCode2IfCourseAlreadyExists() throws Exception {
        // Setup
        Course course = new Course("springBoot", "mandatory course for java engineer");

        when(coursesService.findCourseByName(anyString())).thenReturn(Optional.of(course));
        // Exercise and assert
        String actualString = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(course)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Response actual = OBJECT_MAPPER.readValue(actualString, Response.class);

        // Assert
        assertThat(actual.getStatusCode(), is("2"));
        assertThat(actual.getStatusDesc(), is("Course already exists"));
    }

    @Test
    public void addCourseReturnsStatus0() throws Exception {
        // Setup
        Course course = new Course("springBoot", "mandatory course for java engineer");

        when(coursesService.findCourseByName(anyString())).thenReturn(Optional.empty());
        when(coursesService.saveCourse(course)).thenReturn(course);

        // Exercise and assert
        String actualString = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(course)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Response actual = OBJECT_MAPPER.readValue(actualString, Response.class);

        // Assert
        assertThat(actual.getStatusCode(), is("0"));
        assertThat(actual.getStatusDesc(), is("Ok"));
    }

    @Test
    public void deleteCourseReturnsStatus0() throws Exception {
        // Setup
        Course course = new Course("springBoot", "mandatory course for java engineer");

        when(coursesService.findCourseByName(anyString())).thenReturn(Optional.of(course));
        // Exercise and assert
        String actualString = mockMvc.perform(delete("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(course)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Response actual = OBJECT_MAPPER.readValue(actualString, Response.class);

        // Assert
        assertThat(actual.getStatusCode(), is("0"));
        assertThat(actual.getStatusDesc(), is("Ok"));
    }

    @Test
    public void changeCourseDescriptionReturnsStatus0() throws Exception {
        // Setup
        Course course = new Course("springBoot", "mandatory course for java engineer");

        when(coursesService.findCourseByName(anyString())).thenReturn(Optional.of(course));
        // Exercise and assert
        String actualString = mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(course)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Response actual = OBJECT_MAPPER.readValue(actualString, Response.class);

        // Assert
        assertThat(actual.getStatusCode(), is("0"));
        assertThat(actual.getStatusDesc(), is("Ok"));
    }
}
