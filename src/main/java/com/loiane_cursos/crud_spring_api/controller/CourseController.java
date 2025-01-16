package com.loiane_cursos.crud_spring_api.controller;

import com.loiane_cursos.crud_spring_api.model.Course;
import com.loiane_cursos.crud_spring_api.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
//@AllArgsConstructor //Lombok para criar o constructor
public class CourseController {

    //@Autowired, controller ou setter para criar o construtor
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<Course> list() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Course> findById(@PathVariable @NotNull @Positive Long id) {
        return courseService.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course) {
        //System.out.println(course.getName());
        //return ResponseEntity.status(HttpStatus.CREATED)
        // .body(courseRepository.save(course));
        return courseService.create(course)  ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody Course course) {
        return courseService.update(id, course)
                .map(recordFound ->  ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        if (courseService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.noContent().build();
    }
}
