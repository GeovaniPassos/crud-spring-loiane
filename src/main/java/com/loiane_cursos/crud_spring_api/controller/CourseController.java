package com.loiane_cursos.crud_spring_api.controller;

import com.loiane_cursos.crud_spring_api.model.Course;
import com.loiane_cursos.crud_spring_api.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
//@AllArgsConstructor //Lombok para criar o constructor
public class CourseController {

    //@Autowired, controller ou setter para criar o construtor
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public @ResponseBody List<Course> list() {

        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Course> findById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course) { 
        //System.out.println(course.getName());
        //return ResponseEntity.status(HttpStatus.CREATED)
        // .body(courseRepository.save(course));
        return courseRepository.save(course);
    }
}
