package devnastapi.devnestapi.course.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a course entity stored in the system.
 *
 * <p>
 * This class contains all essential information related to a course, including
 * its name, workload, description, creation date, and last modification date.
 * Each course is identified by a UUID and automatically tracked with auditing
 * timestamps.
 * </p>
 */
@Entity
@Table(name = "course", schema = "public")
public class Course {

    /**
     * Unique identifier of the course.
     * Automatically generated using UUID strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "course")
    private UUID id;

    /**
     * Name of the course.
     * Must not be blank.
     */
    @NotBlank(message = "The course name is mandatory.")
    @Column(name = "nameOfCourse")
    private String nameOfCourse;

    /**
     * Total workload of the course in hours.
     * Must not be null.
     */
    @NotNull(message = "The workload is mandatory.")
    @Column(name = "workload")
    private Integer workload;

    /**
     * Detailed description of the course.
     * Must not be blank.
     */
    @NotBlank(message = "The description is mandatory.")
    @Column(name = "description")
    private String description;

    /**
     * Timestamp of the latest update performed on the course.
     * Automatically handled by Spring Data auditing.
     */
    @LastModifiedDate
    @Column(name = "last_modify")
    private LocalDate lastModify;

    /**
     * Timestamp of the course creation.
     * Automatically handled by Spring Data auditing.
     */
    @CreatedDate
    @Column(name = "create_date")
    private LocalDate createDate;

    // ------------------------------------------------------
    //                    Getters and Setters
    // ------------------------------------------------------

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLastModify() {
        return lastModify;
    }

    public void setLastModify(LocalDate lastModify) {
        this.lastModify = lastModify;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
