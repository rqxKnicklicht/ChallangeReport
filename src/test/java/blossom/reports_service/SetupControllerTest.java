package blossom.reports_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import blossom.reports_service.inbound.Controller.SetupController;
import blossom.reports_service.model.Entities.ChallengeSummary;
import blossom.reports_service.model.Entities.User;
import blossom.reports_service.model.Exceptions.AlreadyExistsException;
import blossom.reports_service.model.Exceptions.NotFoundException;
import blossom.reports_service.model.Repositories.UserRepository;
import blossom.reports_service.model.Services.ReportsService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

@WebMvcTest(SetupController.class)
public class SetupControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ReportsService reportsService;

  @MockBean
  private UserRepository userRepository;

  private User user;
  private ChallengeSummary challengeSummary;

  java.util.Date date = new Date();

  @BeforeEach
  public void setUp() {
    user = new User("example@org.de");
    user.setId(1L);

    challengeSummary = new ChallengeSummary(user);
    challengeSummary.setId(1L);
  }

  // Test for createChallengeSummary with status code 200
  @Test
  public void createChallengeSummaryTest() throws Exception {

    given(this.reportsService.createChallengeSummary(1L)).willReturn(challengeSummary);
    this.mvc.perform(post("/rest/setup/createSummary/{userId}", 1))
        .andDo(print())
        .andExpect(status().isOk());
  }

  // Test for createChallengeSummary with status code 404 when user not found
  @Test
  public void createChallengeSummaryTestUserNotFound() throws Exception {
    given(this.reportsService.createChallengeSummary(1L)).willThrow(new NotFoundException("User not found"));
    this.mvc.perform(post("/rest/setup/createSummary/{userId}", 1))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  // Test for void createChallengeSummary with status code 409 when
  // challengeSummary
  // already exists
  @Test
  public void createChallengeSummaryTestAlreadyExists() throws Exception {
    given(this.reportsService.createChallengeSummary(1L))
        .willThrow(new AlreadyExistsException("ChallengeSummary already exists"));
    this.mvc.perform(post("/rest/setup/createSummary/{userId}", 1))
        .andDo(print())
        .andExpect(status().isConflict());
  }

}
