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
public class TechnologyDTO {
    @PositiveOrZero(message = "Technology ID must be greater than zero.")
    private Long   id;

    @NotEmpty(message = "Technology Name must not be empty.")
    @Size(min = 5, max = 30, message = "Technology  Name length must be between {min} letters and {max} letters.")
    private String name;

    @NotNull
    @NotBlank(message = "Technology  slug must not be empty.")
    @NotEmpty(message = "Technology  slug must not be empty.")
    @Size(min = 3, max = 30, message = "Technology Type Name length must be between {min} letters and {max} letters.")
    private String slug;

    @NotNull
    @NotBlank(message = "Technology  description must not be empty.")
    @NotEmpty(message = "Technology  description must not be empty.")
    @Size(min = 5, max = 150, message = "Technology Type description length must be between {min} letters and {max} letters.")
    private String description;
}
