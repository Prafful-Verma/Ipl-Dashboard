package io.xavior.ipldashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.xavior.ipldashboard.models.Team;
import io.xavior.ipldashboard.repository.TeamRepository;

@RestController
@RequestMapping("/team")
public class TeamNameController {
    
    private TeamRepository teamRepository;

    @Autowired
    public TeamNameController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }    

    @GetMapping("/allTeams")
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @GetMapping("/{teamName}")
    public Team getTeam(@PathVariable("teamName") String TeamName){

        // List<Team> teamList = teamRepository.findAll();

        Team team = teamRepository.getByTeamName(TeamName);

        return team;
    }

}
