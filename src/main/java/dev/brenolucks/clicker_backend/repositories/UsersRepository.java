package dev.brenolucks.clicker_backend.repositories;

import dev.brenolucks.clicker_backend.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findFirstByEmail(String email);
    Optional<Users> findByUsername(String username);
    Optional<Users> findFirstByUsername(String username);
}
