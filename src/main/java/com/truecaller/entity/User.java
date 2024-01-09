package com.truecaller.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")

    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        @Column(name = "first_name", nullable = false)
        private String firstName;

        @Column(name = "last_name", nullable = false)
        private String lastName;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "email", nullable = false)
        private String email;

        @Column(name = "phone_no", nullable = false)
        private String phoneNumber;

        @Column(name = "date_Of_birth")
        private Date dateOfBirth;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Contact> contacts;
    }





