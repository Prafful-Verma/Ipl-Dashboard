package io.xavior.ipldashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.xavior.ipldashboard.models.Match;

public interface MatchRepository extends JpaRepository<Match,Long> {
    
    List<Match> findByMatchWinner(String teamName);
    List<Match> findByMatchWinnerAndTossWinner(String team1,String team2);
    List<Match> findBySeason(String season);
    List<Match> findBySeasonAndTeam1OrTeam2(String season,String team1,String team2);

}
