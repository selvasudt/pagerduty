package com.selvas.service;


import com.selvas.entity.Developer;
import com.selvas.entity.Team;
import com.selvas.model.TeamModel;
import com.selvas.repository.TeamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Team addMembers(TeamModel team) {
        com.selvas.entity.Team team1 = new com.selvas.entity.Team();
        BeanUtils.copyProperties(team.getTeam(), team1);
        Set<Developer> set = new HashSet<>();
        team.getDevelopers().forEach(x -> {
            Developer y = new Developer();
            BeanUtils.copyProperties(x, y);
            set.add(y);
            y.setTeam(team1);
        });
        team1.setDevelopers(set);
        return teamRepository.save(team1);
    }


}
