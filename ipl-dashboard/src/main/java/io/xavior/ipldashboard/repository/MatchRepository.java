package io.xavior.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.xavior.ipldashboard.models.Match;

public interface MatchRepository extends JpaRepository<Match,Long> {
    
    List<Match> findByMatchWinner(String teamName);
    List<Match> findByMatchWinnerAndTossWinner(String team1,String team2);
    List<Match> findBySeason(String season);
    List<Match> findBySeasonAndTeam1OrTeam2(String season,String team1,String team2);

    @Query("SELECT m FROM Match m WHERE m.team1 = :teamName OR m.team2 = :teamName ORDER BY date DESC")
    List<Match> findLatestMatches(@Param("teamName") String teamName, Pageable pageable);
        
    @Query("SELECT m FROM Match m WHERE (m.team1 = :teamName OR m.team2 = :teamName) AND m.date BETWEEN :startDate AND :endDate ORDER BY m.date")
    List<Match> getAllMatchesForTeamInYear(@Param("teamName") String teamName, @Param("startDate")   LocalDate startDate,@Param("endDate") LocalDate endDate);

}
