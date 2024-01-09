package com.truecaller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
@ToString
public class ContactListDtos {

    @Valid
    private List<ContactDto> contactDtos;
}
