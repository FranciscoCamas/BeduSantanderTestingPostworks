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
public class CandidateDTO {

    @PositiveOrZero(message = "Candidate ID must be greater than zero.")
    private Long   id;

    @NotEmpty(message = "Candidate Name must not be empty.")
    @Size(min = 5, max = 30, message = "Candidate Name length must be between {min} letters and {max} letters.")
    private String name;

    @NotNull
    @NotBlank(message = "Candidate Name must not be empty.")
    @NotEmpty(message = "Candidate Name must not be empty.")
    @Size(min = 5, max = 30, message = "Candidate Name length must be between {min} letters and {max} letters.")
    private String last_Name;

    @Email
    @NotNull
    @NotBlank(message = "Candidate mail must not be empty.")
    @NotEmpty(message = "Candidate mail must not be empty.")
    @Size(min = 5, max = 35, message = "Candidate email length must be between {min} letters and {max} letters.")
    private String email;

    @NotNull(message = "You should determine if Candidate the is active or not")
    private Boolean is_active;


    @Override
    public String toString() {
        return "InterviewerDTO{" +
                "name='" + name + '\'' +
                ", last_name='" + last_Name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
