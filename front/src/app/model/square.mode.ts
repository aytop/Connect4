import * as fromPlayer from './player.model';

export class Square {
  player: fromPlayer.Player;
  row: number;
  column: number;

  constructor(row: number, column: number) {
    this.player = new fromPlayer.Empty();
    this.row = row;
    this.column = column;
  }
}
