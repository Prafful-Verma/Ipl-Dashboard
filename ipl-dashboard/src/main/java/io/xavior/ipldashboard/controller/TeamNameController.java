package io.xavior.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import io.xavior.ipldashboard.models.Match;
import io.xavior.ipldashboard.models.Team;
import io.xavior.ipldashboard.repository.MatchRepository;
import io.xavior.ipldashboard.repository.TeamRepository;

@RestController
@RequestMapping("/team")
@CrossOrigin
public class TeamNameController {
    
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    @Autowired
    public TeamNameController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/allTeams")
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    @GetMapping("/{teamName}")
    public Team getTeam(@PathVariable("teamName") String TeamName){

        Team team = teamRepository.getByTeamName(TeamName);

        //Get Latest Matches
        Pageable pageable = PageRequest.of(0, 4);
        List<Match> Latestmatches = matchRepository.findLatestMatches(TeamName, pageable);
        team.setLatestMatches(Latestmatches);

        return team;
    }


    @GetMapping(value="/totalMatches/{matches}")
    public List<Team> getTeamsByTotalMaches(@PathVariable("matches") Long matches) {
        List<Team> teams = teamRepository.getTeamByTotalMatches(matches);
        return teams;   
    }

    @GetMapping(value="/totalMatches/{matches}&&{wins}")
    public List<Team> getTeamsByTotalMachesandtotalWin(@PathVariable("matches") Long matches, @PathVariable("wins") Long wins) {
        List<Team> teams = teamRepository.getTeamByTotalMatchesandTotalWins(matches,wins);
        return teams;   
    }

    @GetMapping("/{teamName}/matches")
    public List<Match> getMatchbyYear(@PathVariable("teamName") String teamName, @RequestParam int year){
        
        LocalDate startDate = LocalDate.of(year,1,1);
        LocalDate endDate = LocalDate.of(year+1,1,1);

        return matchRepository.getAllMatchesForTeamInYear(teamName, startDate, endDate);
    }
    

}
