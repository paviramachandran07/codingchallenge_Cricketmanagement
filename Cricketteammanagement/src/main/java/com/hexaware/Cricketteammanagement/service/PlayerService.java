package com.hexaware.Cricketteammanagement.service;

import java.util.List;

import com.hexaware.Cricketteammanagement.Entity.Player;
import com.hexaware.Cricketteammanagement.dto.PlayerDTO;

public interface PlayerService {
   
	Player createPlayer(PlayerDTO playerDTO);
	List<PlayerDTO>getAllPlayers();
	PlayerDTO getPlayerById(Long id);
	PlayerDTO updatePlayer(Long id,PlayerDTO playerDTO);
	void deletePlayer(Long id);
}
