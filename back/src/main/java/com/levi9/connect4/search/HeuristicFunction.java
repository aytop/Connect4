package com.levi9.connect4.search;

import com.levi9.connect4.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.levi9.connect4.service.GameService;

@Component
public class HeuristicFunction
{
	@Autowired
	private GameService gameService;
	
	public int compute(Board board)
	{
		return gameService.getNumberOfThreats(board, 1, 2) -
		       gameService.getNumberOfThreats(board, 2, 2) +
		       3 * gameService.getNumberOfThreats(board, 1, 3) -
		       3 * gameService.getNumberOfThreats(board, 2, 3);
	}
}
