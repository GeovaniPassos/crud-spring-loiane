package com.loiane_cursos.crud_spring_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loiane_cursos.crud_spring_api.enums.Category;
import com.loiane_cursos.crud_spring_api.enums.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull @Length(min = 5, max = 100) String name,
        @NotNull @Length(max=10) @ValueOfEnum(enumClass = Category.class) String category,
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}
