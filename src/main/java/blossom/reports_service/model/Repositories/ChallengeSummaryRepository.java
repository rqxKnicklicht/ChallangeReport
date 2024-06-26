package blossom.reports_service.model.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blossom.reports_service.model.Entities.ChallengeSummary;
import blossom.reports_service.model.Entities.User;

@Repository
public interface ChallengeSummaryRepository extends JpaRepository<ChallengeSummary, Long> {

  // find
  Optional<ChallengeSummary> findById(Long id);

  Optional<ChallengeSummary> findByUser(User user);

  Optional<ChallengeSummary> findByUserId(Long userId);

  // delete
  void deleteById(Long id);

  // save
  ChallengeSummary save(ChallengeSummary challengeSummary);

  // get all

  List<ChallengeSummary> findAll();

}
