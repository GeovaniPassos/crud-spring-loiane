package com.loiane_cursos.crud_spring_api.enums;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}




