import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component';
import { SquareComponent } from './square/square.component';
import { HttpClientModule } from '@angular/common/http';
import { StoreModule } from '@ngrx/store';
import { appReducer } from './app.store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from 'src/environments/environment';
import { EffectsModule } from '@ngrx/effects';
import { BoardEffects } from './board/store/board.effects';
import { GameOverComponent } from './game-over/game-over.component';

@NgModule({
  declarations: [AppComponent, BoardComponent, SquareComponent, GameOverComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    StoreModule.forRoot(appReducer),
    StoreDevtoolsModule.instrument({
      logOnly: environment.production,
    }),
    EffectsModule.forRoot([BoardEffects]),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
