package blossom.reports_service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class ReportsService {

  private final ActivityReportRepository activityReportRepository;
  private final ActivitySummaryRepository activitySummaryRepository;
  private final UserRepository userRepository;

  @Autowired
  public ReportsService(ActivityReportRepository activityReportRepository,
      ActivitySummaryRepository activitySummaryRepository, UserRepository userRepository) {
    this.activityReportRepository = activityReportRepository;
    this.activitySummaryRepository = activitySummaryRepository;
    this.userRepository = userRepository;
  }

  // create activitySummary for a new user
  public ActivitySummary creActivitySummary(Long userId, Date date) {
    var user = userRepository.findById(userId);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    ArrayList<Activity> activities = new ArrayList<Activity>();
    var activitySummary = new ActivitySummary(user.get(), activities);
    activitySummaryRepository.save(activitySummary);
    return activitySummary;
  }

  // create activityReport for a new activity
  public ActivityReport createActivityReport(Activity activity, Long userId, String name, Date startDate, Date endDate,
      String createdBy, String description) {
    Optional<ActivityReport> activityReportOptional = activityReportRepository.findByActivity(activity);
    if (activityReportOptional.isPresent()) {
      throw new ActivityReportAlreadyExistsException("Activity report already exists");
    }
    var userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    var user = userOptional.get();
    var activityReport = new ActivityReport(activity, user, name, startDate, createdBy, description);
    activityReportRepository.save(activityReport);
    return activityReport;
  }
}
