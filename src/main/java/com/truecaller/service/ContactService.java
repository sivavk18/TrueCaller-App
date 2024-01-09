package com.truecaller.service;

import com.truecaller.dto.ContactDto;
import com.truecaller.dto.ContactListDtos;
import com.truecaller.dto.UserDTO;
import com.truecaller.entity.Contact;
import com.truecaller.entity.Mobile;
import com.truecaller.entity.User;
import com.truecaller.exception.ContactNotFoundException;
import com.truecaller.exception.UserNotFoundException;
import com.truecaller.mapper.ContactsMapper;
import com.truecaller.repository.ContactsRepository;
import com.truecaller.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContactService {
    @Autowired
    ContactsRepository contactRepo;

    @Autowired
    UserRepository userRepo;

    private final ContactsMapper contactsMapper = ContactsMapper.INSTANCE;


    public List<ContactDto> saveContacts(List<ContactDto> contactDtoList, Integer userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        User user = userOptional.get();
        Contact contact1 = contactsMapper.toMobileNumber(contactDtoList.get(0));
        List<Contact> contacts = contactsMapper.toMobileNumbers(contactDtoList);
        contacts.forEach(contact -> {
            contact.setUser(user);
        });
        List<Contact> savedContacts = contactRepo.saveAll(contacts);

        return contactsMapper.toMobileNumberDto(savedContacts);
    }



    public ContactDto saveContact(ContactDto userDTO) {
        Contact contacts = contactsMapper.toMobileNumber(userDTO);
        Contact savedUser =contactRepo.save(contacts);
        return contactsMapper.toDto(savedUser);
    }


    public List<ContactDto> getAllTitleTemplate() {
        List<Contact> phones = contactRepo.findAll();
        return contactsMapper.toMobileNumberDto(phones);
    }

    public ContactDto update(ContactDto contactDto, Integer contactId) throws ContactNotFoundException {
        Contact contact1 = contactRepo.findById(contactId).orElseThrow(() -> new ContactNotFoundException("No user by ID: " + contactId));
        contact1.setId(contactDto.getId());
        contact1.setName(contactDto.getName());
        contact1.setNickName(contactDto.getNickName());
        Contact update = contactRepo.save(contact1);
        return contactsMapper.toDto(update);
    }

    public ContactDto updatePhone(ContactDto contactDto, Integer contactId, Integer userId) {
        // Find the Contact entity
        Optional<User> optionalUser =  userRepo.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new  UserNotFoundException("user not found");
        }
        User user = optionalUser.get();

        // Find the PhoneBook entity
        Optional<Contact> optionalContact = contactRepo.findById(contactId);
        if ( optionalContact.isEmpty()) {
            throw new ContactNotFoundException("contactId not found");
        }
        Contact contact1 = optionalContact.get();

        // Update the phone number
        contact1.setId(contactDto.getId());
        contact1.setName(contactDto.getName());
        contact1.setNickName(contactDto.getNickName());
        // Save the updated PhoneBook entity
        contactRepo.save(contact1);

        // Map the updated PhoneBook entity to DTO and return
        return contactsMapper.toDto(contact1);
    }
    public void deleteByUserIdAndContactId(Integer userId, Integer contactId) throws ContactNotFoundException {
        userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        boolean deleted = false;
        Optional<Contact> contact = contactRepo.findById(contactId);
        if (contact.isPresent()) {
            Contact c = contact.get();
            if(c.getUser().getId() == userId) {
                contactRepo.delete(c);
                deleted = true;
            }
        }

        if(!deleted){
            throw new UserNotFoundException("Contact not found with User ID: " + userId + ", Contact ID: " + contactId);
        }
    }


}





