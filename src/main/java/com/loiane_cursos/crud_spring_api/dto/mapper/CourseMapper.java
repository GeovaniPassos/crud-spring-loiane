package com.loiane_cursos.crud_spring_api.dto.mapper;

import com.loiane_cursos.crud_spring_api.dto.CourseDTO;
import com.loiane_cursos.crud_spring_api.dto.LessonDTO;
import com.loiane_cursos.crud_spring_api.enums.Category;
import com.loiane_cursos.crud_spring_api.model.Course;
import com.loiane_cursos.crud_spring_api.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .toList();
        return new CourseDTO(course.getId(),
                course.getName(),
                course.getCategory().getValue(),
                lessons);
    }

    public Course toEntity(CourseDTO courseDTO) {

        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        //TODO: use a mapper of category
        course.setCategory(convertCategoryValue(courseDTO.category()));

        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));

        List<Lesson> lessons = courseDTO.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
            lesson.setCourse(course);
            return lesson;
        }).collect(Collectors.toList());
        course.setLessons(lessons);

        return course;

        //Builder pattern (Como ultilizar o builder)
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida" + value);
        };
    }

}
