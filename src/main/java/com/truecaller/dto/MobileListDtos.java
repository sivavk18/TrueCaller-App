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
public class MobileListDtos {
    @Valid
    private List<MobileDTO> mobileDtos;
}
