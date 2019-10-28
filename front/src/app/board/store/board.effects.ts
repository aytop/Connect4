import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { HttpClient, HttpParams } from '@angular/common/http';
import * as boardActions from './board.actions';
import { State } from './board.reducer';
import { Store } from '@ngrx/store';
import { environment } from 'src/environments/environment';
import { switchMap, map, catchError, tap } from 'rxjs/operators';
import { of } from 'rxjs';
import { AppState } from 'src/app/app.store';
import { PlayerOne, PLAYER_ONE, PLAYER_TWO, PlayerTwo } from 'src/app/model/player.model';

@Injectable()
export class BoardEffects {
  private boardState: State;
  constructor(private actions$: Actions, private http: HttpClient, private store: Store<AppState>) {
    this.store.select('board').subscribe(state => (this.boardState = state));
  }

  @Effect({ dispatch: false })
  endTurn = this.actions$.pipe(
    ofType(boardActions.INSERT_DISK),
    tap((action: boardActions.InsertDisk) => {
      if (action.payload.human) {
        this.store.dispatch(new boardActions.GetMove());
      }
    }),
  );


  @Effect()
  fetchMove = this.actions$.pipe(
    ofType(boardActions.GET_MOVE),
    switchMap(() => {
      return this.http
        .get<{ player: number; columnNumber: number; column: any }>(`${environment.serverUrl}/api/moves`, {
          params: new HttpParams()
            .set('state', this.boardState.board.toString())
            .append('playerToMove', '' + this.boardState.playerToMove.id),
        })
        .pipe(
          map(move => {
            return new boardActions.InsertDisk({ column: move.columnNumber, human: false });
          }),
          catchError(error => {
            console.log(error);
            return of(new boardActions.GetMoveError());
          }),
        );
    }),
  );
}
