package com.hexaware.Cricketteammanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.Cricketteammanagement.Entity.Player;
import com.hexaware.Cricketteammanagement.dto.PlayerDTO;
import com.hexaware.Cricketteammanagement.service.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    
    @PostMapping
    public Player createPlayer(@Valid @RequestBody PlayerDTO dto) {
        return playerService.createPlayer(dto);
    }

   
    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    
    @PutMapping("/{id}")
    public PlayerDTO updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerDTO dto) {
        return playerService.updatePlayer(id, dto);
    }

   
    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return "Player deleted successfully";
    }
}

