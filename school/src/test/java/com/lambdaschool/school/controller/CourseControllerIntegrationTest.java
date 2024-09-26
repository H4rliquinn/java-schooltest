package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    //    GET /courses/courses Response Time
    @Test
    public void GivenResponseTimeForGetAllCourses()
    {
        given().when().get("/courses/courses").then().time(lessThan(5000L));
    }

    @Test
    public void GivenaddNewCourse() throws Exception
    {

        Instructor i1= new Instructor("Sally");
        i1.setInstructid(1);
        Course c1 = new Course( "New Stuff", i1);
        c1.setCourseid(100);

        ObjectMapper mapper = new ObjectMapper();
        String stringc1 = mapper.writeValueAsString(c1);

        given().contentType("application/json").body(stringc1).when().post("/courses/course/add").then().statusCode(201);
    }
}