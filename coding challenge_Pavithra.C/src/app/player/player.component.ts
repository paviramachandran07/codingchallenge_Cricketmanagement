import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../player.service';
import { Player } from '../models/player.model';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {
  players: Player[] = [];
  newPlayer: Player = { name: '', jerseyNumber: 0, role: '', totalMatches: 0, teamName: '', country: '', description: '' };
  editing: boolean = false;
  showTable: boolean = false;

  constructor(private playerService: PlayerService) {}

  ngOnInit(): void {}

  fetchPlayers(): void {
    this.playerService.getAllPlayers().subscribe(data => {
      this.players = data;
      this.showTable = true;
    });
  }

  displayPlayers(): void {
    this.fetchPlayers();
  }

  savePlayer(): void {
    if (!this.newPlayer.name || !this.newPlayer.jerseyNumber || !this.newPlayer.role || !this.newPlayer.teamName || !this.newPlayer.country) {
      alert('Please fill in all required fields.');
      return;
    }

    if (this.editing) {
      this.playerService.updatePlayer(this.newPlayer).subscribe(() => {
        this.fetchPlayers();
        this.cancel();
        alert('Player updated!');
      });
    } else {
      this.playerService.addPlayer(this.newPlayer).subscribe(() => {
        this.fetchPlayers();
        this.cancel();
        alert('Player added!');
      });
    }
  }

  editPlayer(player: Player): void {
    this.newPlayer = { ...player };
    this.editing = true;
  }

  deletePlayer(id: number | undefined): void {
    if (!id) {
      alert('Cannot delete player: ID is missing.');
      return;
    }
    if (confirm('Are you sure you want to delete this player?')) {
      this.playerService.deletePlayer(id).subscribe(() => {
        this.fetchPlayers();
        alert('Player deleted!');
      });
    }
  }

  cancel(): void {
    this.newPlayer = { name: '', jerseyNumber: 0, role: '', totalMatches: 0, teamName: '', country: '', description: '' };
    this.editing = false;
  }
}
