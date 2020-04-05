package com.psy.realestatehomeland.repository;

import com.psy.realestatehomeland.model.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> getUserEntityByEmail(String email);


}
