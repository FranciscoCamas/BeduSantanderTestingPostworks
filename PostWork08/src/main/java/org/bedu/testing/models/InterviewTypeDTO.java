package org.bedu.testing.models;

import lombok.*;

import javax.validation.constraints.*;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class InterviewTypeDTO {
    @PositiveOrZero(message = "Interview Type ID must be greater than zero.")
    private Long   id;

    @NotEmpty(message = "Interview Type Name must not be empty.")
    @Size(min = 5, max = 30, message = "Interview Type Name length must be between {min} letters and {max} letters.")
    private String name;

    @NotNull
    @NotBlank(message = "Interview Type slug must not be empty.")
    @NotEmpty(message = "Interview Type slug must not be empty.")
    @Size(min = 3, max = 30, message = "Interview Type Name length must be between {min} letters and {max} letters.")
    private String slug;

    @NotNull
    @NotBlank(message = "Interview Type description must not be empty.")
    @NotEmpty(message = "Interview Type description must not be empty.")
    @Size(min = 5, max = 150, message = "Interview Type description length must be between {min} letters and {max} letters.")
    private String description;
}