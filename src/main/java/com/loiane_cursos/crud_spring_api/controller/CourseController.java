package com.loiane_cursos.crud_spring_api.controller;

import com.loiane_cursos.crud_spring_api.model.Course;
import com.loiane_cursos.crud_spring_api.repository.CourseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor //Lombok para criar o construtor
public class CourseController {

    //@Autowired, controller ou setter para criar o construtor
    private final CourseRepository courseRepository;
    /*
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }*/

    @GetMapping
    public @ResponseBody List<Course> list() {
        return courseRepository.findAll();
    }
}
