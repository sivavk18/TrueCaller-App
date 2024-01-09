package com.truecaller.mapper;


import com.truecaller.dto.ContactDto;
import com.truecaller.entity.Contact;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactsMapper {
    ContactsMapper INSTANCE = Mappers.getMapper(ContactsMapper.class);
    List<ContactDto> toMobileNumberDto(List<Contact> contactDO);

    List<Contact>toMobileNumbers(List<ContactDto> contactDto);
    ContactDto toDto(Contact contact);

    Contact toMobileNumber(ContactDto contactDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contact updateMobileNumberDO(Contact newContactDO, @MappingTarget Contact oldContactDO);
}
