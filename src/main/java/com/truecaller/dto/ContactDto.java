package com.truecaller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
@ToString
public class ContactDto {

    private Integer id;

//    @Valid
    @NotBlank(message = "Invalid name: Empty nickname")
    @NotNull(message = "Invalid name: nickname is NULL")
    @Size(min = 3, max = 30, message = "Invalid nickName: Must be of 2 - 30 characters")
    private String nickName;

//    @Valid
    @NotBlank(message = "Invalid name: Empty name")
    @NotNull(message = "Invalid name: name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String name;




}
