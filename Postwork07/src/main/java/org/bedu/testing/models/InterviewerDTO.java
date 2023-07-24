package org.bedu.testing.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
/**
 * @author Francisco Javier Camas Tec francisco_camas@chotmail.com
 */

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InterviewerDTO {

    @PositiveOrZero(message = "Client ID must be greater than zero.")
    private Long   id;

    @NotEmpty(message = "Client Name must not be empty.")
    @Size(min = 5, max = 30, message = "Client Name length must be between {min} letters and {max} letters.")
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty(message = "Client Name must not be empty.")
    @Size(min = 5, max = 30, message = "Client Name length must be between {min} letters and {max} letters.")
    private String last_name;

    @Email
    private String email;

    @NotNull(message = "You determine if Interviewer the is active or not")
    @NotBlank(message = "You determine if Interviewer the is active or not")
    @NotEmpty(message = "You determine if Interviewer the is active or not")
    private boolean is_active;

    @NotNull(message = "You determine if Interviewer the is a administrator or not")
    @NotBlank(message = "You determine if Interviewer the is a administrator or not")
    @NotEmpty(message = "You determine if Interviewer the is a administrator or not")
    private boolean is_admin;
}
