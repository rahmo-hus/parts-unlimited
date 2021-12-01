package net.croz.unlimited.parts.repository;

import net.croz.unlimited.parts.model.users.ERole;
import net.croz.unlimited.parts.model.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}