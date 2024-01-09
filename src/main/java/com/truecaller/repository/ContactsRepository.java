package com.truecaller.repository;

import com.truecaller.entity.Contact;
import com.truecaller.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAll();

    List<Contact>findByName(@Param("name")String name);

    List<Contact>findByNickName(@Param("nickName")String nickName);

}
