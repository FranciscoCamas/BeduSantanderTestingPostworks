package org.bedu.testing.models;

import lombok.*;

import javax.validation.constraints.*;

/**
 * @author Francisco Javier Camas Tec francisco_camas@chotmail.com
 */

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DisciplineDTO {
    @PositiveOrZero(message = "Discipline ID must be greater than zero.")
    private Long   id;

    @NotEmpty(message = "Discipline Name must not be empty.")
    @Size(min = 5, max = 30, message = "Discipline Name length must be between {min} letters and {max} letters.")
    private String name;

    @NotNull
    @NotBlank(message = "Discipline slug must not be empty.")
    @NotEmpty(message = "Discipline slug must not be empty.")
    @Size(min = 3, max = 30, message = "Discipline Name length must be between {min} letters and {max} letters.")
    private String slug;

    @NotNull
    @NotBlank(message = "Discipline description must not be empty.")
    @NotEmpty(message = "Discipline description must not be empty.")
    @Size(min = 5, max = 150, message = "Discipline description length must be between {min} letters and {max} letters.")
    private String description;
}
