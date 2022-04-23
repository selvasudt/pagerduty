package com.selvas.service;


import com.selvas.entity.Developer;
import com.selvas.model.Notification;
import com.selvas.model.TeamModel;
import com.selvas.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {


    @Autowired
    RestTemplate restTemplate;
    @Autowired
    TeamRepository teamRepository;

    public boolean Notify(Long id) {
        Optional<com.selvas.entity.Team> team = teamRepository.findById(id);
        Developer team1 = team.get().getDevelopers().stream().findAny().get();
        HttpEntity<Notification> request = new HttpEntity<>(new Notification("Too many alerts received on the xxx issue.!",team1.getPhoneNumber()));
        restTemplate.postForObject("https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f", request, TeamModel.class);
        return true;

    }
}
