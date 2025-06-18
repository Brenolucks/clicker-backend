package dev.brenolucks.clicker_backend.repositories;

import dev.brenolucks.clicker_backend.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<UserDetails> findByEmail(String email);
    Optional<UserDetails> findByUsername(String username);

    Optional<Users> findRandomNumberByUsername(String username);
}
