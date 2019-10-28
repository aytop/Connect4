import { Action } from '@ngrx/store';
import { Player } from 'src/app/model/player.model';

export const INSERT_DISK = '[Board] INSERT_DISK';
export const GET_MOVE = '[Board] GET_MOVE';
export const GET_MOVE_ERROR = '[Board] GET_MOVE_ERROR';
export const WINS = '[Board] WINS';
export const DRAW = '[Board] DRAW';
export const NEW_GAME = '[Board] NEW_GAME';

export class InsertDisk implements Action {
  readonly type = INSERT_DISK;
  constructor(public payload: { column: number; human: boolean }) {}
}
export class GetMove implements Action {
  readonly type = GET_MOVE;
}
export class GetMoveError implements Action {
  readonly type = GET_MOVE_ERROR;
}
export class Wins implements Action {
  readonly type = WINS;
  constructor(public payload: { player: Player }) {}
}
export class NewGame implements Action {
  readonly type = NEW_GAME;
}
export class Draw implements Action {
  readonly type = DRAW;
}
export type BoardActions = InsertDisk | GetMove | GetMoveError | Wins | NewGame | Draw;
