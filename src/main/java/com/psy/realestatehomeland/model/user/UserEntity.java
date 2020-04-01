package com.psy.realestatehomeland.model.user;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user",schema = "sc")
public class UserEntity implements User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2userGen" )
    @GenericGenerator(name = "uuid2userGen", strategy = "uuid2")
    private String id;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Column(name = "enabled")
    private Boolean isEnabled;

    @Column(name = "user_role")
    private UserRole role;


}
