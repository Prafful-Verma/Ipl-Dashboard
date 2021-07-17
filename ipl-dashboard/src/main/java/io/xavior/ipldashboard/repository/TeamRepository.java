package io.xavior.ipldashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.xavior.ipldashboard.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    
    public Team getByTeamName(String teamName);

    public List<Team> getByTeamNameOrTeamName(String team1, String team2);    

}
