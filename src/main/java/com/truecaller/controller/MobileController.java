package com.truecaller.controller;


import com.truecaller.dto.ApiResponseDTO;
import com.truecaller.dto.ContactDto;
import com.truecaller.dto.MobileDTO;
import com.truecaller.dto.MobileListDtos;
import com.truecaller.exception.ContactNotFoundException;
import com.truecaller.exception.UserNotFoundException;
import com.truecaller.service.MobileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MobileController {
    private final MobileService mobileService;


    @PostMapping("/{userId}/contact")
    public ResponseEntity<List<MobileDTO>> createContact(@PathVariable Integer contactId, @Valid @RequestBody MobileListDtos mobileListDtos) throws UserNotFoundException {
        List<MobileDTO> mobileDtoList = mobileListDtos.getMobileDtos();
        List<MobileDTO> contact= mobileService.addmobile(mobileDtoList,contactId);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @GetMapping("/contact/{contactId}")
    public ResponseEntity<MobileDTO>getById(@PathVariable Integer mobileId) throws ContactNotFoundException {
        MobileDTO contact = mobileService.getById(mobileId);
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }

    @GetMapping("/contact")
    public List<MobileDTO> listContact(){
        return mobileService.getAllBYId();
    }

    @GetMapping("phonebbok/{phone}")
    public ResponseEntity<ContactDto> getPhoneBookName(
            @PathVariable String phone) {
        ContactDto contactDto = mobileService.findNameByNumber(phone);
        return new ResponseEntity<>(contactDto,HttpStatus.OK);

    }

    @GetMapping("phonebook/name/{name}")
    public ResponseEntity<MobileDTO> getPhoneBookNumber(
            @PathVariable String name) {
        MobileDTO mobileDto = mobileService.findNumberByName(name);
        return new ResponseEntity<>(mobileDto,HttpStatus.OK);

    }

    @GetMapping("phonebook/nickname/{nickName}")
    public ResponseEntity<MobileDTO> getPhoneNumber(
            @PathVariable String nickName) {
        MobileDTO mobileDto = mobileService.findNumberByNickName(nickName);
        return new ResponseEntity<>(mobileDto,HttpStatus.OK);

    }

   @PutMapping("/contact/{contactId}/{mobileId}") public ResponseEntity<MobileDTO>update (@PathVariable Integer contactId,@PathVariable Integer mobileId, @Valid @RequestBody MobileDTO mobileDTO) throws ContactNotFoundException {
      MobileDTO update = mobileService.updatePhone(mobileDTO,contactId,mobileId);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{contactId}")
    public ApiResponseDTO deleteById(@PathVariable Integer contactId) throws ContactNotFoundException {
        mobileService.delete(contactId);
        return new ApiResponseDTO("contact successfully deleted",true, HttpStatus.OK);
    }

}
