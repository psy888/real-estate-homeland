package com.psy.realestatehomeland.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User_Role",
        uniqueConstraints = {
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = {"User_Id", "Role_Id"})}, schema = "sc")
@Data
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id", nullable = false)
    private UserEntity appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_Id", nullable = false)
    private AppRole appRole;
}

