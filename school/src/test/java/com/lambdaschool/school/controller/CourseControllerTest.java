package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value=CourseController.class,secure=false)
public class CourseControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList;

    @Before
    public void setUp() throws Exception
    {
        courseList = new ArrayList<>();

//        String instructname

        Instructor i1= new Instructor("Sally");
        i1.setInstructid(1);
        Instructor i2= new Instructor("Lucy");
        i1.setInstructid(2);
        Instructor i3= new Instructor("Charlie");
        i1.setInstructid(3);

//        String coursename, Instructor instructor

        Course c1 = new Course( "Data Science", i1);
        c1.setCourseid(1);
        Course c2 = new Course( "JavaScript", i1);
        c1.setCourseid(2);
        Course c3 = new Course( "Node.js", i1);
        c1.setCourseid(3);
        Course c4 = new Course( "Java Back End", i2);
        c1.setCourseid(4);
        Course c5 = new Course( "Mobile IOS", i2);
        c1.setCourseid(5);
        Course c6 = new Course( "Mobile Android", i3);
        c1.setCourseid(6);

        courseList.add(c1);
        courseList.add(c2);
        courseList.add(c3);
        courseList.add(c4);
        courseList.add(c5);
        courseList.add(c6);

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void listAllCourses() throws Exception
    {
        String apiUrl = "/courses/courses";
        Mockito.when(courseService.findAll()).thenReturn(courseList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);
        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getCourseById()
    {
    }

    @Test
    public void getCountStudentsInCourses()
    {
    }

    @Test
    public void deleteCourseById()
    {
    }


}