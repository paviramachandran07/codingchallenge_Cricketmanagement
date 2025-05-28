package com.hexaware.cricketeammanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cricketeammanagement.dto.PlayerDTO;
import com.hexaware.cricketeammanagement.entity.Player;
import com.hexaware.cricketeammanagement.exception.ResourceNotFoundException;
import com.hexaware.cricketeammanagement.service.PlayerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/players")
@Validated
public class PlayerController {

    private static final String NUMBER2 = "number";
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
    
 
// Task given to get the players name by jersey number and the num should be between 1 to 99
    
    @GetMapping("/jersey/{number}")
    public ResponseEntity<Player> getPlayerByJerseyNumber(
            @PathVariable(NUMBER2) @Min(1) @Max(99) int number) {

        Player player = playerService.getPlayerByJerseyNumber(number)
            .orElseThrow(() -> new ResourceNotFoundException("Player with jersey number " + number + " not found"));

        return ResponseEntity.ok(player);
    }

}

