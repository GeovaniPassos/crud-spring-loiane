package com.loiane_cursos.crud_spring_api.service;

import com.loiane_cursos.crud_spring_api.exception.RecordNotFoundException;
import com.loiane_cursos.crud_spring_api.model.Course;
import com.loiane_cursos.crud_spring_api.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(@Valid Course course) {
        //System.out.println(course.getName());
        //return ResponseEntity.status(HttpStatus.CREATED)
        // .body(courseRepository.save(course));
        return courseRepository.save(course);
    }

    public Course update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {

        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));

        /*
         courseRepository.findById(id)
            .map(recordFound -> {
                courseRepository.deleteById(id);
                return true;
            }).orElseThrow(() -> new RecordNotFoundException(id));

        */
    }
}
