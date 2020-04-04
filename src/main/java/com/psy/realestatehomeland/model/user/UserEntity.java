package com.psy.realestatehomeland.model.user;

import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user",schema = "sc",uniqueConstraints = { //
        @UniqueConstraint(name = "USER_EMAIL_UK", columnNames = "email") })
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
    @Column(name = "phone_number")
    private String phone;

    @Transient
    private String password;

    @NotNull
    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Column(name = "enabled")
    private Boolean isEnabled;

//    @Column(name = "user_role")
//    private UserRole role;

    @Transient
    private String role;
}
