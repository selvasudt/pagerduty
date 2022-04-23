package com.selvas.controller;

import com.selvas.model.TeamModel;
import com.selvas.service.NotificationService;
import com.selvas.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {


    @Autowired
    TeamService teamService;

    @Autowired
    NotificationService notificationService;

    @PostMapping(value = "/addteammembers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addTeamMembers(@RequestBody TeamModel team) {

        com.selvas.entity.Team team1 = teamService.addMembers(team);
        if (team1 != null) {
            return ResponseEntity.ok().body("Added Successfully.!");
        }
        return ResponseEntity.status(503).body("Something went wrong.");

    }


    @PostMapping("alert/{id}")
    public ResponseEntity alertNotification(@PathVariable("id") Integer id) {
        if (notificationService.Notify(id.longValue())) {
            return ResponseEntity.ok().body("Notification sent Successfully.!");
        }
        return ResponseEntity.ok().body("Something went wrong.!");
    }
}
