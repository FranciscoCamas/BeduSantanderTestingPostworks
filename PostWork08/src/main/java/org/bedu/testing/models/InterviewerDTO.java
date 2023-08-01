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
public class InterviewerDTO {

    @PositiveOrZero(message = "Interviewer ID must be greater than zero.")
    private Long   id;

    @NotEmpty(message = "Interviewer Name must not be empty.")
    @Size(min = 5, max = 30, message = "Interviewer Name length must be between {min} letters and {max} letters.")
    private String name;

    @NotNull
    @NotBlank(message = "Interviewer Name must not be empty.")
    @NotEmpty(message = "Interviewer Name must not be empty.")
    @Size(min = 5, max = 30, message = "Interviewer Name length must be between {min} letters and {max} letters.")
    private String last_Name;

    @Email
    @NotNull
    @NotBlank(message = "Interviewer mail must not be empty.")
    @NotEmpty(message = "Interviewer mail must not be empty.")
    @Size(min = 5, max = 35, message = "Interviewer email length must be between {min} letters and {max} letters.")
    private String email;

    @NotNull(message = "You should determine if Interviewer the is active or not")
    private Boolean is_active;

    @NotNull(message = "You should determine if Interviewer the is a administrator or not")
    private Boolean is_admin;

    @Override
    public String toString() {
        return "InterviewerDTO{" +
                "name='" + name + '\'' +
                ", last_name='" + last_Name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}