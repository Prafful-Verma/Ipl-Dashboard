package io.xavior.ipldashboard.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tid;
    
    private String teamName;
    private Long totalMatches;
    private Long totalWins;
    @Transient
    private List<Match> latestMatches;


    public Team(String teamName, Long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }
    

}
