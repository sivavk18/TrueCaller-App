package com.truecaller.mapper;

import com.truecaller.dto.MobileDTO;
import com.truecaller.entity.Mobile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MobilesMapper {
    MobilesMapper INSTANCE = Mappers.getMapper(MobilesMapper.class);

    List<MobileDTO> toContactDto(List<Mobile> mobileDo);
    Mobile toModel(MobileDTO mobileDTO);

    MobileDTO toDTO(Mobile mobile);
}
