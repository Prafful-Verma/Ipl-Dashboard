package io.xavior.ipldashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.xavior.ipldashboard.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    
    public Team getByTeamName(String teamName);

    public List<Team> getByTeamNameOrTeamName(String team1, String team2);  

    @Query("SELECT t FROM Team t where t.totalMatches > ?1 ORDER BY totalMatches")
    public List<Team> getTeamByTotalMatches(Long matches);  

    @Query("SELECT t FROM Team t where t.totalMatches > ?1 AND t.totalWins > ?2 ORDER BY totalMatches")
    public List<Team> getTeamByTotalMatchesandTotalWins(Long matches, Long wins);  
    

}

// @Query("SELECT t FROM Team t where t.totalMatches > :matches")