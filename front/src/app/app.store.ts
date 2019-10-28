import * as fromBoard from './board/store/board.reducer';
import { ActionReducerMap } from '@ngrx/store';

export interface AppState {
  board: fromBoard.State;
}

export const appReducer: ActionReducerMap<AppState> = {
  board: fromBoard.boardReducer,
};
