package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.user.AppRole;
import com.psy.realestatehomeland.model.user.UserEntity;
import com.psy.realestatehomeland.model.user.UserRole;
import com.psy.realestatehomeland.repository.AppRoleRepository;
import com.psy.realestatehomeland.repository.UserRepository;
import com.psy.realestatehomeland.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final AppRoleRepository appRoleRepository;

    public void createUser(UserEntity user) {

        UserRole ur = new UserRole();
        AppRole ar = appRoleRepository
                .findByRoleName(user.getRole()).orElseThrow(() -> new IllegalArgumentException("Illegal User role name: " + user.getRole()));
        ur.setAppRole(ar);
        user.setEncryptedPassword(
                new BCryptPasswordEncoder()
                        .encode(user.getPassword()));
        user.setIsEnabled(true);
        ur.setAppUser(user);



        userRepository.save(user);
        userRoleRepository.save(ur);
    }

    public UserEntity findUserByEmail(String email) {
        return userRepository.getUserEntityByEmail(email).orElse(null);
    }
}
