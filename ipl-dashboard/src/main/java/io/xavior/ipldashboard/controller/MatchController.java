package io.xavior.ipldashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.xavior.ipldashboard.models.Match;
import io.xavior.ipldashboard.repository.MatchRepository;

@RestController
@RequestMapping("/match")
public class MatchController {
    
    private MatchRepository matchRepository;

    @Autowired
    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping("/matchWinner/{teamName}")
    public List<Match> getMatchWinnerTeams(@PathVariable("teamName") String teamName){
        return matchRepository.findByMatchWinner(teamName);
    }

    @GetMapping("/matchandtossWinner/{teamName}")
    public List<Match> getMatchWinnerAndTossWinner1(@PathVariable("teamName") String teamName){
        return matchRepository.findByMatchWinnerAndTossWinner(teamName,teamName);
    }

    @GetMapping("/matchandtossWinner/{team1}&&{team2}")
    public List<Match> getMatchWinnerAndTossWinner2(@PathVariable("team1") String teamName1, @PathVariable("team2") String teamName2){
        return matchRepository.findByMatchWinnerAndTossWinner(teamName1,teamName2);
    }

    @GetMapping("/season/{season}")
    public List<Match> getMatchbySeason(@PathVariable("season") String season){
        return matchRepository.findBySeason(season);
    }



}
