package com.psy.realestatehomeland.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "App_Role",
        uniqueConstraints = {
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name")}, schema = "sc")
@Data
@NoArgsConstructor
public class AppRole {

    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    private Long roleId;

    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
}