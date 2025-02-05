package com.loiane_cursos.crud_spring_api.dto;

import java.util.List;

public record CoursePageDTO(List<CourseDTO> curses, long totalElements, int totalPages) {

}
