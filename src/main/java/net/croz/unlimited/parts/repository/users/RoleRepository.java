package net.croz.unlimited.parts.repository.users;

import net.croz.unlimited.parts.models.users.ERole;
import net.croz.unlimited.parts.models.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}