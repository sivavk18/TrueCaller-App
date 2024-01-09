package com.truecaller.repository;

import com.truecaller.entity.Contact;
import com.truecaller.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MobilesRepository extends JpaRepository<Mobile,Integer> {

    @Query(value="  SELECT mb.countrycode , mb.phone_no , cnt.name , cnt.nick_name  FROM mobiles mb  JOIN contact cnt  ON cnt.id=mb.contact_id "
            + "	WHERE mb.phone_no=:contactno ", nativeQuery=true)
    List<Mobile> getdataList(@Param("contactno") String contactno);


//
    List<Mobile> findByPhoneNumber(@Param("phone") String phone);

    List<Mobile> findByContacts(Contact contacts);
}
