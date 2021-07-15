package io.xavior.ipldashboard.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.xavior.ipldashboard.models.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager entityManager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      HashMap<String, Team> teamData = new HashMap<>();
      // Find Total Matches
      // Total Matches as Team1
      List<Object[]> TeamCountAsFirst = entityManager
          .createQuery("SELECT team1, COUNT(*) FROM match GROUP BY team1", Object[].class).getResultList();
      for (Object[] itr : TeamCountAsFirst) {
        String teamName = (String) itr[0];
        Long totalcount1 = (Long) itr[1];
        Team team = new Team(teamName, totalcount1);
        teamData.put(teamName, team);
      }

      // Total Matches as Team2
      List<Object[]> TeamCountAsSecond = entityManager
          .createQuery("SELECT team2, COUNT(*) FROM match GROUP BY team2", Object[].class).getResultList();
      for (Object[] itr : TeamCountAsSecond) {
        String teamName = (String) itr[0];
        Long totalcount2 = (Long) itr[1];
        Team team = teamData.get(teamName);
        if (team != null) {
          team.setTotalMatches(team.getTotalMatches() + totalcount2);
        }else{
          team = new Team(teamName, totalcount2);
          teamData.put(teamName, team);
        }
      }

      // Find Total Wins
      List<Object[]> WinTeamCount = entityManager
          .createQuery("SELECT matchWinner, COUNT(*) FROM match GROUP BY matchWinner", Object[].class).getResultList();
      for (Object[] itr : WinTeamCount) {
        String teamName = (String) itr[0];
        Long wincount1 = (Long) itr[1];
        Team team = teamData.get(teamName);
        if (team != null) {
          team.setTotalWins(wincount1);
        }else{
          System.out.println("Testing Null");
        }
      }


      for (Team teamitr : teamData.values()) {
        log.info(teamitr.toString());
        entityManager.persist(teamitr);
      }

    }
  }
}

// Using JDBC

// private final JdbcTemplate jdbcTemplate;

// @Autowired
// public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
// this.jdbcTemplate = jdbcTemplate;
// }

// jdbcTemplate.query("SELECT team1, team2, date, match_winner FROM match",
// (rs, row) -> "Team1 : " + rs.getString(1) + ", Team2 : " + rs.getString(2) +
// ", Date : " + rs.getDate(3) + ", Winner : " + rs.getString(4)
// ).forEach( match -> log.info(match.toString()));

// Entity Manager
// List<Object[]> l = entityManager.createQuery("SELECT team1, team2, date,
// matchWinner FROM match", Object[].class)
// .getResultList();
// for (Object[] itr : l) {
// log.info("Team1 : " + itr[0] + ", Team2 : " + itr[1] + ", Date : " + itr[2] +
// ", Winner : " + itr[3]);
// }