export const EMPTY = 0;
export const PLAYER_ONE = 1;
export const PLAYER_TWO = 2;

export class PlayerOne {
  public id = PLAYER_ONE;
}

export class PlayerTwo {
  public id = PLAYER_TWO;
}

export class Empty {
  public id = EMPTY;
}
export type Player = PlayerOne | PlayerTwo | Empty;

export function getPlayerById(id: number) {
  switch (id) {
    case EMPTY:
      return new Empty();
    case PLAYER_ONE:
      return new PlayerOne();
    case PLAYER_TWO:
      return new PlayerTwo();
    default:
      return null;
  }
}
export function randomPlayer() {
  return getPlayerById(Math.round(Math.random() + 1));
}
export function nextPlayer(player: Player) {
  return getPlayerById(3 - player.id);
}
