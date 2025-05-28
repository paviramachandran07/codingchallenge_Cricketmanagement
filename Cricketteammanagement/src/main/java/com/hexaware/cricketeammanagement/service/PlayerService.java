package com.hexaware.cricketeammanagement.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.cricketeammanagement.dto.PlayerDTO;
import com.hexaware.cricketeammanagement.entity.Player;

public interface PlayerService {
   
	Player createPlayer(PlayerDTO playerDTO);
	List<PlayerDTO>getAllPlayers();
	PlayerDTO getPlayerById(Long id);
	PlayerDTO updatePlayer(Long id,PlayerDTO playerDTO);
	void deletePlayer(Long id);
	 Optional<Player> getPlayerByJerseyNumber(int jerseyNumber);
}
