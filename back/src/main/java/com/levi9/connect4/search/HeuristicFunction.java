package com.levi9.connect4.search;

import com.levi9.connect4.entity.Board;
import com.levi9.connect4.entity.Threat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.levi9.connect4.service.GameService;

import java.util.List;

@Component
public class HeuristicFunction
{
	@Autowired
	private GameService gameService;
	
	public int compute(Board board)
	{
		return gameService.getThreats(board, 1, 2).size() -
		       gameService.getThreats(board, 2, 2).size() +
		       3 * gameService.getThreats(board, 1, 3).size() -
		       3 * gameService.getThreats(board, 2, 3).size() +
		       5 * getOddThreats(board) - 5 * getEvenThreats(board);
	}
	private int getOddThreats(Board board)
	{
		int result =0;
		List<Threat> threats= gameService.getThreats(board, 1, 3);
		for(Threat threat: threats)
		{
			if(threat.isOdd())
			{
				result++;
			}
		}
		return result;
	}
	private int getEvenThreats(Board board)
	{
		int result =0;
		List<Threat> threats = gameService.getThreats(board,2,3);
		for(Threat threat: threats)
		{
			if(threat.isEven())
			{
				result++;
			}
		}
		return result;
	}
}
