package com.hexaware.Cricketteammanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.Cricketteammanagement.Entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
