package com.loiane_cursos.crud_spring_api.repository;

import com.loiane_cursos.crud_spring_api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
