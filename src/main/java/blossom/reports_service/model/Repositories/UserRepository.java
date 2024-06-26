package blossom.reports_service.model.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import blossom.reports_service.model.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User save(User user);

}
