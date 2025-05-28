package com.hexaware.cricketeammanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.cricketeammanagement.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	Optional<Player> findByJerseyNumber(int jerseyNumber);

}
