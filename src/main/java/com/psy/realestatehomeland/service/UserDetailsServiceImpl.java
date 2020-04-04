package com.psy.realestatehomeland.service;

import com.psy.realestatehomeland.model.user.UserEntity;
import com.psy.realestatehomeland.repository.AppRoleRepository;
import com.psy.realestatehomeland.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AppRoleRepository appRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.getUserEntityByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " was not found in the database"));


        System.out.println("Found User: " + userEntity);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleRepository.getRoleNames(userEntity.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), grantList);

    }
}
