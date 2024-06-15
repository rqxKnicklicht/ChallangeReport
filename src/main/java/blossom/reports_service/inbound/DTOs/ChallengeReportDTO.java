package blossom.reports_service.inbound.DTOs;

import java.util.Date;
import java.util.List;

import blossom.reports_service.model.Entities.Challenge;
import blossom.reports_service.model.Entities.ChallengeProgress;
import blossom.reports_service.model.Entities.ChallengeReport;
import blossom.reports_service.model.Entities.User;
import blossom.reports_service.model.Enums.ChallengeStatus;

public class ChallengeReportDTO {

  private Long id;
  private Challenge challenge;
  private User user;
  private List<ChallengeProgress> progressList;
  private Date startDate;
  private Date endDate;
  private ChallengeStatus status;
  private int version;

  public ChallengeReportDTO() {
  }

  public ChallengeReportDTO(Challenge challenge, User user, List<ChallengeProgress> progressList, Date startDate,
      Date endDate, ChallengeStatus status, int version) {
    this.challenge = challenge;
    this.user = user;
    this.progressList = progressList;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
    this.version = version;
  }

  public ChallengeReportDTO(ChallengeReport challengeReport) {
    this.id = challengeReport.getId();
    this.challenge = challengeReport.getChallenge();
    this.user = challengeReport.getUser();
    this.progressList = challengeReport.getProgressList();
    this.startDate = challengeReport.getStartDate();
    this.endDate = challengeReport.getEndDate();
    this.status = challengeReport.getStatus();
    this.version = challengeReport.getVersion();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Challenge getChallenge() {
    return challenge;
  }

  public void setChallenge(Challenge challenge) {
    this.challenge = challenge;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<ChallengeProgress> getProgressList() {
    return progressList;
  }

  public void setProgressList(List<ChallengeProgress> progressList) {
    this.progressList = progressList;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public ChallengeStatus getStatus() {
    return status;
  }

  public void setStatus(ChallengeStatus status) {
    this.status = status;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public void addProgress(ChallengeProgress progress) {
    this.progressList.add(progress);
  }

  public void removeProgress(ChallengeProgress progress) {
    this.progressList.remove(progress);
  }

  public void clearProgress() {
    this.progressList.clear();
  }
}