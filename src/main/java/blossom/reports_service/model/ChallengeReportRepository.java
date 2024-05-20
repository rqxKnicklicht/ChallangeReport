package blossom.reports_service.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeReportRepository extends JpaRepository<ChallengeReport, Long> {

  // find
  Optional<ChallengeReport> findById(Long id);

  Optional<ChallengeReport> findByChallenge(Challenge challenge);

  Iterable<ChallengeReport> findAllByStatus(Enum status);

  Iterable<ChallengeReport> findAllByUser(User user);

  // delete
  void deleteById(Long id);

  void deleteByName(String name);

  // save
  ChallengeReport save(ChallengeReport challengeReport);

  Iterable<ChallengeReport> findAllByUserId(Long userId);

  boolean existsByChallengeIdAndUserId(Long challengeId, Long userId);

}