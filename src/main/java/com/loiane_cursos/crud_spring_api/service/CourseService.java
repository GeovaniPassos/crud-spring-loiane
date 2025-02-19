package com.loiane_cursos.crud_spring_api.service;

import com.loiane_cursos.crud_spring_api.dto.CourseDTO;
import com.loiane_cursos.crud_spring_api.dto.CoursePageDTO;
import com.loiane_cursos.crud_spring_api.dto.mapper.CourseMapper;
import com.loiane_cursos.crud_spring_api.exception.RecordNotFoundException;
import com.loiane_cursos.crud_spring_api.model.Course;
import com.loiane_cursos.crud_spring_api.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDTO list(@PositiveOrZero int page,@Positive @Max(100) int size) {
        Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(page, size));
        List<CourseDTO> courses = pageCourse.get().map(courseMapper::toDTO).collect(Collectors.toList());
        return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
    }

    /*public List<CourseDTO> list() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }*/

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        //System.out.println(course.getName());
        //return ResponseEntity.status(HttpStatus.CREATED)
        // .body(courseRepository.save(course));
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(this.courseMapper.convertCategoryValue(courseDTO.category()));
                    //recordFound.setLessons(course.getLessons());
                    recordFound.getLessons().clear();
                    course.getLessons().forEach(recordFound.getLessons()::add);
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@Valid @NotNull @Positive Long id) {

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
