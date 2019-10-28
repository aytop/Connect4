import { Component, OnInit } from '@angular/core';
import { Board } from '../model/board.model';
import { Square } from '../model/square.mode';
import * as fromPlayer from '../model/player.model';
import { Store } from '@ngrx/store';
import * as boardActions from './store/board.actions';
import { AppState } from '../app.store';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {
  board: Board;
  player: fromPlayer.Player;
  gameOver: boolean;
  winningPlayer: fromPlayer.Player;
  constructor(private store: Store<AppState>) {
    this.store.select('board').subscribe(state => {
      this.board = state.board;
      this.player = state.playerToMove;
      this.gameOver = state.gameOver;
      this.winningPlayer = state.winningPlayer;
      if (!state.gameOver) {
        if (this.board.isDraw()) {
          this.store.dispatch(new boardActions.Draw());
        }
        if (this.board.isWinner(fromPlayer.PLAYER_ONE)) {
          this.store.dispatch(new boardActions.Wins({ player: new fromPlayer.PlayerOne() }));
        }
        if (this.board.isWinner(fromPlayer.PLAYER_TWO)) {
          this.store.dispatch(new boardActions.Wins({ player: new fromPlayer.PlayerTwo() }));
        }
      }
    });
  }

  ngOnInit() {
    if (Math.random() <= 0.5) {
      this.store.dispatch(new boardActions.InsertDisk({ column: 3, human: false }));
    }
  }

  onClick(square: Square) {
    if (this.board.isLegal(square.column)) {
      this.store.dispatch(new boardActions.InsertDisk({ column: square.column, human: true }));
    }
  }
}
