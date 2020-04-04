package com.psy.realestatehomeland.repository;

import com.psy.realestatehomeland.model.user.AppRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppRoleRepository extends CrudRepository<AppRole,Long> {

    Optional<AppRole> findByRoleName(String name);

    @Query("Select ur.appRole.roleName from UserRole ur where ur.appUser.id = ?1")
    List<String> getRoleNames(String userId);
}
