package com.truecaller.service;




import com.truecaller.dto.ContactDto;
import com.truecaller.dto.MobileDTO;
import com.truecaller.entity.Contact;
import com.truecaller.entity.Mobile;
import com.truecaller.exception.ContactNotFoundException;
import com.truecaller.mapper.ContactsMapper;
import com.truecaller.mapper.MobilesMapper;
import com.truecaller.repository.ContactsRepository;
import com.truecaller.repository.MobilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MobileService {
    private final MobilesRepository mobilesRepository;

    private final ContactsRepository contactRepository;

    private final MobilesMapper mobilesMapper = MobilesMapper.INSTANCE;
    private final ContactsMapper contactsMapper = ContactsMapper.INSTANCE;

    public List<MobileDTO> addmobile(List<MobileDTO> mobileDtoList, Integer contactId) throws ContactNotFoundException {
        Contact contacts = contactRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException("No user by ID: " + contactId));
        List<Mobile> mobileToSave = new ArrayList<>();
        for (MobileDTO mobileDTO : mobileDtoList) {
            Mobile mobileDetail = mobilesMapper.toModel(mobileDTO);
            mobileDetail.setContacts(contacts);
            mobileToSave.add(mobileDetail);
        }

        List<Mobile> savedContact = mobilesRepository.saveAll(mobileToSave);

        return mobilesMapper.toContactDto(savedContact);
    }

    public MobileDTO getById(Integer contactId) throws ContactNotFoundException {
        Mobile mobile = mobilesRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException("No user by ID: " + contactId));
        return mobilesMapper.toDTO(mobile);
    }

    public List<MobileDTO> getAllBYId() {
        return mobilesRepository.findAll()
                .stream()
                .map(mobilesMapper::toDTO)
                .collect(Collectors.toList());
    }


    public void delete(Integer contactId) throws ContactNotFoundException {
        mobilesRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException("No user by ID: " + contactId));
        mobilesRepository.deleteById(contactId);
    }

    public ContactDto findNameByNumber(String phoneNumber) {

        List<Mobile> mobileList = mobilesRepository.findByPhoneNumber(phoneNumber);
        List<Contact> contactList  = new ArrayList<>();
        for(Mobile m: mobileList){
            Optional<Contact> c = contactRepository.findById(m.getContacts().getId());
            if(c.isPresent()) {
                contactList.add(c.get());
            }
        }

        int maxCount = 0;
        Contact maxCountContact = null;

        if (contactList.size() >= 1) {
            Map<String, Integer> nameCountMap = new HashMap<>();

            for (Contact contact : contactList) {
                String nameKey = contact.getName() + "-" + contact.getNickName();
                Integer count = nameCountMap.getOrDefault(nameKey, 0);
                count++;
                nameCountMap.put(nameKey, count);

                if (count > maxCount){
                    maxCount = count;
                    maxCountContact = contact;
                }
            }

            // Return the max count name record
            return contactsMapper.toDto( maxCountContact);
        } else {
            return new ContactDto();
        }
    }

    public MobileDTO findNumberByName(String name){
        List<Contact> contactList = contactRepository.findByName(name);
        //if more than one pick first record
        if(contactList != null && contactList.size() >= 1){
            Contact contact = contactList.get(0);
            List<Mobile> mobiles = mobilesRepository.findByContacts(contact);
            //if more than one pick first record
            if(mobiles != null && mobiles.size() >= 1){
                Mobile mobile = mobiles.get(0);
                return mobilesMapper.toDTO(mobile);
            }
        }
        return new MobileDTO();
    }

    public MobileDTO findNumberByNickName(String nickName){
        List<Contact> contactList = contactRepository.findByNickName(nickName);
        //if more than one pick first record
        if(contactList != null && contactList.size() >= 1){
            Contact contact = contactList.get(0);
            List<Mobile> mobiles = mobilesRepository.findByContacts(contact);
            //if more than one pick first record
            if(mobiles != null && mobiles.size() >= 1){
                Mobile mobile = mobiles.get(0);
                return mobilesMapper.toDTO(mobile);
            }
        }
        return new MobileDTO();
    }

    public MobileDTO updatePhone(MobileDTO mobileDTO, Integer contactId, Integer mobileId) {
        // Find the Contact entity
        Optional<Mobile> optionalMobile =  mobilesRepository.findById(mobileId);
        if (optionalMobile.isEmpty()) {
            throw new ContactNotFoundException("Contact not found");
        }
        Mobile mobile = optionalMobile.get();

        // Find the PhoneBook entity
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if ( optionalContact.isEmpty()) {
            throw new ContactNotFoundException("PhoneBook entry not found");
        }
        Contact contact = optionalContact.get();

        // Update the phone number
        mobile.setId(mobileDTO.getId());
        mobile.setCountryCode(mobileDTO.getCountryCode());
        mobile.setPhoneNumber(mobileDTO.getPhoneNumber());
        mobile.setDateCreated(mobileDTO.getDateCreated());
        // Save the updated PhoneBook entity
        mobilesRepository.save(mobile);

        // Map the updated PhoneBook entity to DTO and return
        return mobilesMapper.toDTO(mobile);
    }
}



