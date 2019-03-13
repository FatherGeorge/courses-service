package com.example.coursesservice;

import java.util.List;

public class Response {
    private String statusCode;
    private String statusDesc;
    private List<Course> courses;
    private Course course;

    public Response setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public Response setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
        return this;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public Response setCourses(List<Course> courses) {
        this.courses = courses;
        return this;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Response setCourse(Course course) {
        this.course = course;
        return this;
    }

    public Course getCourse() {
        return course;
    }
}
