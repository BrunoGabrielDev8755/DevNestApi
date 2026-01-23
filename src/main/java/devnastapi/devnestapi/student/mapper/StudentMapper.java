package devnastapi.devnestapi.student.mapper;

import devnastapi.devnestapi.student.dto.StudentDto;
import devnastapi.devnestapi.student.model.Student;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link Student} entities and {@link StudentDto} DTOs.
 *
 * <p>This interface defines mapping operations between the domain model ({@link Student})
 * and the data transfer object ({@link StudentDto}) using MapStruct. MapStruct will
 * automatically generate the implementation at compile time.</p>
 *
 * <p>The mapper follows these conventions:
 * <ul>
 *   <li>Field names between source and target must match for automatic mapping</li>
 *   <li>Custom mapping logic can be added via additional methods or {@code @Mapping} annotations</li>
 *   <li>The generated implementation is a Spring component due to {@code componentModel = "spring"}</li>
 * </ul></p>
 *
 * <p><strong>Note:</strong> The method {@link #ToEntity(StudentDto)} starts with an uppercase 'T',
 * which deviates from Java naming conventions. Consider renaming to {@code toEntity} for consistency
 * with {@link #toDto(Student)}.</p>
 *
 * @author Your Name or Team Name
 * @version 1.0
 * @since 2024-01-01
 *
 * @see Mapper
 * @see Student
 * @see StudentDto
 * @see <a href="https://mapstruct.org/">MapStruct Documentation</a>
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {

    /**
     * Converts a {@link StudentDto} to a {@link Student} entity.
     *
     * <p>This method maps all fields from the DTO to the corresponding fields
     * in the entity. MapStruct will generate an implementation that performs
     * the field-by-field mapping at compile time.</p>
     *
     * <p><strong>Mapping Details:</strong>
     * <ul>
     *   <li>{@code StudentDto.id()} → {@code Student.id()}</li>
     *   <li>{@code StudentDto.name()} → {@code Student.name()}</li>
     *   <li>{@code StudentDto.email()} → {@code Student.email()}</li>
     *   <li>{@code StudentDto.birthDate()} → {@code Student.birthDate()}</li>
     *   <li>{@code StudentDto.password()} → {@code Student.password()}</li>
     * </ul>
     * Additional custom mappings can be defined using {@code @Mapping} annotations.</p>
     *
     * @param dto the data transfer object to convert, must not be {@code null}
     * @return a new {@link Student} entity with values mapped from the DTO
     *
     * @throws NullPointerException if {@code dto} is {@code null}
     *
     * @see StudentDto
     * @see Student
     */
    Student ToEntity(StudentDto dto);

    /**
     * Converts a {@link Student} entity to a {@link StudentDto}.
     *
     * <p>This method maps all fields from the entity to the corresponding fields
     * in the DTO. MapStruct will generate an implementation that performs
     * the field-by-field mapping at compile time.</p>
     *
     * <p><strong>Mapping Details:</strong>
     * <ul>
     *   <li>{@code Student.id()} → {@code StudentDto.id()}</li>
     *   <li>{@code Student.name()} → {@code StudentDto.name()}</li>
     *   <li>{@code Student.email()} → {@code StudentDto.email()}</li>
     *   <li>{@code Student.birthDate()} → {@code StudentDto.birthDate()}</li>
     *   <li>{@code Student.password()} → {@code StudentDto.password()}</li>
     * </ul>
     * Additional custom mappings can be defined using {@code @Mapping} annotations.</p>
     *
     * <p><strong>Security Note:</strong> This mapping includes the password field.
     * Consider using {@code @Mapping(target = "password", ignore = true)} or creating
     * a separate DTO without sensitive fields for API responses.</p>
     *
     * @param student the entity to convert, must not be {@code null}
     * @return a new {@link StudentDto} with values mapped from the entity
     *
     * @throws NullPointerException if {@code student} is {@code null}
     *
     * @see Student
     * @see StudentDto
     */
    StudentDto toDto(Student student);
}