package com.truecaller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
@ToString
public class UserDTO {

    private Integer id;

    @NotBlank(message = "Invalid Firstname: Empty Firstname")
    @NotNull(message = "Invalid Firstname: Firstname is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String firstName;

    @NotBlank(message = "Invalid lastname: Empty lastname")
    @NotNull(message = "Invalid lastname: lastname is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String lastName;

    @NotBlank(message = "Invalid email: Empty email")
    @NotNull(message="email should not be null")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}" ,message="invalid email n.o added")
    private String email;

    @NonNull
    @NotNull(message = "Password must be between 4 to 15 characters")
    @Size(min = 4, max = 15)
    private String password;

    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message="dob should not be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;


}
