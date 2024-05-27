package blossom.reports_service.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.micrometer.common.lang.NonNull;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // find
  @NonNull
  Optional<User> findById(Long id);

  Optional<User> findByEmail(String email);

  // delete
  void deleteById(Long id);

  // save
  User save(User user);
}