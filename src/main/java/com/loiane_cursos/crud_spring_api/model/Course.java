package com.loiane_cursos.crud_spring_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@Table(name = "curso")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String category;

}
