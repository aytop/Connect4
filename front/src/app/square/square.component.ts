import { Component, OnInit, Input } from '@angular/core';
import { Square } from '../model/square.mode';
import * as fromPlayer from '../model/player.model';

@Component({
  selector: 'app-square',
  templateUrl: './square.component.html',
  styleUrls: ['./square.component.css'],
})
export class SquareComponent implements OnInit {
  @Input() square: Square;

  get color() {
    switch (this.square.player.id) {
      case fromPlayer.PLAYER_ONE:
        return 'yellow';
      case fromPlayer.PLAYER_TWO:
        return 'red';
      default:
        return 'white';
    }
  }
  constructor() {}

  ngOnInit() {}
}
