package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest
{

    @Autowired
    CourseService courseService;

    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findCourseById()
    {
        assertEquals("JavaScript",courseService.findCourseById(2L).getCoursename());
    }

    @Test
    public void z_deleteFound()
    {
        courseService.delete(3L);
        assertEquals(5,courseService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void z_deleteNotFound()
    {
        courseService.delete(100L);
    }
}