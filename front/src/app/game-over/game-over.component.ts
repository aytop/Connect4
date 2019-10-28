import { Component, OnInit, Input } from '@angular/core';
import { Player } from '../model/player.model';
import { Store } from '@ngrx/store';
import { AppState } from '../app.store';
import { NewGame } from '../board/store/board.actions';

@Component({
  selector: 'app-game-over',
  templateUrl: './game-over.component.html',
  styleUrls: ['./game-over.component.css'],
})
export class GameOverComponent implements OnInit {
  @Input() player: Player;
  constructor(private store: Store<AppState>) {}

  ngOnInit() {}

  onNewGame() {
    this.store.dispatch(new NewGame());
  }
}
