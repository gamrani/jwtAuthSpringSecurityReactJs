package com.api.repositories;

import com.api.models.Role;
import com.api.type.RoleName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {
    Optional<Role> findByName(RoleName roleName);

}
