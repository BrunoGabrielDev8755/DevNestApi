package devnastapi.devnestapi.course.dto;

import java.time.LocalDate;

public record CourseDto(String name
        , int workload
        , String description
        , LocalDate createDate
        , LocalDate lastModify) {

    @Override
    public LocalDate createDate() {
        return createDate;
    }

    @Override
    public LocalDate lastModify() {
        return lastModify;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public int workload() {
        return workload;
    }

    @Override
    public String name() {
        return name;
    }
}
