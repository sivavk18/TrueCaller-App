package com.truecaller.controller;


import com.truecaller.dto.ApiResponseDTO;
import com.truecaller.dto.ContactDto;
import com.truecaller.dto.ContactListDtos;
import com.truecaller.exception.ContactNotFoundException;
import com.truecaller.exception.UserNotFoundException;
import com.truecaller.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    ContactService contactsService;




    @PostMapping("/{userId}/save")
    public ResponseEntity<List<ContactDto>> savePhoneBookEntries(
            @RequestBody @Valid ContactListDtos contactListDtos,
            @PathVariable("userId") Integer userId) {

        List<ContactDto> contactDtoList = contactListDtos.getContactDtos();
        List<ContactDto> savedPhoneBookEntries = contactsService.saveContacts(contactDtoList, userId);
        return ResponseEntity.ok(savedPhoneBookEntries);
    }


    @PostMapping("/{userId}/save2")
    public ResponseEntity<List<ContactDto>> savePhoneBookEntries2(
            @RequestBody @Valid  List<ContactDto> contactDtoList,
            @PathVariable("userId") Integer userId) {
        List<ContactDto> savedPhoneBookEntries = contactsService.saveContacts(contactDtoList, userId);
        return ResponseEntity.ok(savedPhoneBookEntries);
    }


    @PostMapping("/phone/{userId}")
    public ResponseEntity <ContactDto> createStudent(@RequestBody @Valid ContactDto userDTO) {
        ContactDto student= contactsService.saveContact(userDTO);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
    @PutMapping("/phone/{contactId}")
    public ResponseEntity <ContactDto> updatePhone(
            @RequestBody ContactDto contactDtos,
            @PathVariable Integer contactId
    ) {
        ContactDto updatedPhoneBooks = contactsService.update(contactDtos, contactId);
        return new ResponseEntity<>(updatedPhoneBooks, HttpStatus.OK);
    }

    @PutMapping("/phone/{contactId}/{userId}") public ResponseEntity<ContactDto>update (@PathVariable Integer contactId, @PathVariable Integer userId, @Valid @RequestBody ContactDto contactDto) throws ContactNotFoundException {
        ContactDto contactDto1 =contactsService.updatePhone(contactDto,contactId,userId);
        return new ResponseEntity<>(contactDto1, HttpStatus.OK);
    }
//
//    @DeleteMapping("/{contactId}/{userId}")
//    public void deleteContact(@PathVariable Integer contactId, @RequestParam Integer userId) throws ContactNotFoundException {
//        contactsService.delete(contactId, userId);
//    }

    @DeleteMapping("/{userId}/{contactId}")
    public ApiResponseDTO deleteByUserIdAndContactId(@PathVariable Integer userId, @PathVariable Integer contactId) throws UserNotFoundException {
        contactsService.deleteByUserIdAndContactId(userId, contactId);
        return new ApiResponseDTO("user contact successfully deleted",true, HttpStatus.OK);
    }

}
