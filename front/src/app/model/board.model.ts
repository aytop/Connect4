import { Square } from './square.mode';
import * as fromPlayer from './player.model';

export class Board {
  squares: Square[][];

  constructor(board: Board = null) {
    this.squares = [];
    for (let i = 5; i >= 0; i--) {
      const row = [];
      for (let j = 0; j < 7; j++) {
        const square = new Square(i, j);
        if (board !== null) {
          square.player = fromPlayer.getPlayerById(board.squares[5 - i][j].player.id);
        }
        row.push(square);
      }
      this.squares.push(row);
    }
  }

  insert(column: number, player: fromPlayer.Player) {
    const newBoard = new Board(this);
    for (let height = 5; height >= 0; height--) {
      if (newBoard.squares[height][column].player.id === fromPlayer.EMPTY) {
        newBoard.squares[height][column].player = player;
        return newBoard;
      }
    }
    return newBoard;
  }

  toString() {
    let boardString = '';
    for (const row of this.squares) {
      let rowString = '';
      for (const square of row) {
        rowString += square.player.id;
      }
      boardString = rowString + boardString;
    }
    return boardString;
  }

  isLegal(column: number): boolean {
    for (let height = 5; height >= 0; height--) {
      if (this.squares[height][column].player.id === fromPlayer.EMPTY) {
        return true;
      }
    }
    return false;
  }
  isDraw() {
    for (const square of this.squares[0]) {
      if (square.player.id === fromPlayer.EMPTY) {
        return false;
      }
    }
    return true;
  }

  isWinner(player: number) {
    if (this.checkForWin(player, 0, 7, 0, 0, 3, 1)) {
      return true;
    }
    if (this.checkForWin(player, 0, 4, 1, 0, 6, 0)) {
      return true;
    }
    if (this.checkForWin(player, 0, 4, 1, 0, 3, 1)) {
      return true;
    }
    if (this.checkForWin(player, 3, 7, -1, 0, 3, 1)) {
      return true;
    }
  }
  private checkForWin(
    player: number,
    widthStart: number,
    widthEnd: number,
    widthStep: number,
    heightStart: number,
    heightEnd: number,
    heightStep: number,
  ): boolean {
    for (let height = heightStart; height < heightEnd; height++) {
      for (let width = widthStart; width < widthEnd; width++) {
        if (this.squares[height][width].player.id === player) {
          let canBeWin = true;
          for (let offset = 1; offset < 4 && canBeWin; offset++) {
            canBeWin = this.squares[height + heightStep * offset][width + widthStep * offset].player.id === player;
          }
          if (canBeWin) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
