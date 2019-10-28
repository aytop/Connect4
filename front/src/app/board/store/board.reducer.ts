import { Board } from 'src/app/model/board.model';
import * as fromPlayer from 'src/app/model/player.model';
import * as boardActions from './board.actions';

export interface State {
  board: Board;
  playerToMove: fromPlayer.Player;
  loading: boolean;
  error: boolean;
  gameOver: boolean;
  winningPlayer: fromPlayer.Player;
  draw: boolean;
}

const initState: State = {
  board: new Board(),
  playerToMove: new fromPlayer.PlayerOne(),
  loading: false,
  error: false,
  gameOver: false,
  winningPlayer: null,
  draw: false,
};

export function boardReducer(state: State = initState, action: boardActions.BoardActions): State {
  switch (action.type) {
    case boardActions.INSERT_DISK:
      return {
        ...state,
        board: state.board.insert(action.payload.column, state.playerToMove),
        playerToMove: fromPlayer.nextPlayer(state.playerToMove),
        loading: false,
        error: false,
      };
    case boardActions.GET_MOVE:
      return {
        ...state,
        loading: true,
      };
    case boardActions.GET_MOVE_ERROR:
      return {
        ...state,
        loading: false,
        error: true,
      };
    case boardActions.WINS:
      return {
        ...state,
        gameOver: true,
        winningPlayer: action.payload.player,
      };
    case boardActions.NEW_GAME:
      return {
        ...initState,
      };
    case boardActions.DRAW:
      return {
        ...state,
        gameOver: true,
        draw: true,
      };
    default:
      return {
        ...state,
      };
  }
}
