package login.LoginSystem.repositories;

import login.LoginSystem.entities.ERole;
import login.LoginSystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
Optional<Role>findByName(ERole name);
}
