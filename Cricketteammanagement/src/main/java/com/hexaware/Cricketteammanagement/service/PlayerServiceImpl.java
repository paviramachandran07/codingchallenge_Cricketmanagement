package com.hexaware.Cricketteammanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.Cricketteammanagement.dto.PlayerDTO;
import com.hexaware.Cricketteammanagement.Entity.Player;
import com.hexaware.Cricketteammanagement.exception.ResourceNotFoundException;
import com.hexaware.Cricketteammanagement.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepo.findAll();
        List<PlayerDTO> dtos = new ArrayList<>();

        for (Player p : players) {
            PlayerDTO dto = convertToDTO(p);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
        return convertToDTO(player);
    }

    @Override
    public Player createPlayer(PlayerDTO dto) {
        Player player = convertToEntity(dto);
        Player saved = playerRepo.save(player);
        return (saved);
    }

    @Override
    public PlayerDTO updatePlayer(Long id, PlayerDTO dto) {
        Player player = playerRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));

        player.setName(dto.getName());
        player.setJerseyNumber(dto.getJerseyNumber());
        player.setRole(dto.getRole());
        player.setTotalMatches(dto.getTotalMatches());
        player.setTeamName(dto.getTeamName());
        player.setCountry(dto.getCountry());
        player.setDescription(dto.getDescription());

        Player updated = playerRepo.save(player);
        return convertToDTO(updated);
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = playerRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
        playerRepo.delete(player);
    }

    // Helper Methods
    private PlayerDTO convertToDTO(Player p) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setJerseyNumber(p.getJerseyNumber());
        dto.setRole(p.getRole());
        dto.setTotalMatches(p.getTotalMatches());
        dto.setTeamName(p.getTeamName());
        dto.setCountry(p.getCountry());
        dto.setDescription(p.getDescription());
        return dto;
    }

    private Player convertToEntity(PlayerDTO dto) {
        Player p = new Player();
        p.setName(dto.getName());
        p.setJerseyNumber(dto.getJerseyNumber());
        p.setRole(dto.getRole());
        p.setTotalMatches(dto.getTotalMatches());
        p.setTeamName(dto.getTeamName());
        p.setCountry(dto.getCountry());
        p.setDescription(dto.getDescription());
        return p;
    }
}



