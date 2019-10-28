package com.levi9.connect4.search;

import com.levi9.connect4.entity.Board;
import com.levi9.connect4.entity.Move;
import com.levi9.connect4.entity.Occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.levi9.connect4.service.GameService;

import java.util.List;

@Component
public class MinMax
{
	private final int MAX_DEPTH = 5;
	
	@Autowired
	private HeuristicFunction heuristicFunction;
	
	@Autowired
	private GameService gameService;
	
	public Move getNextMove(Board board, int playerToMove)
	{
		return minimax(board, 0, playerToMove).getMoveToMake();
	}
	private SearchResult minimax(Board board, int depth, int player)
	{
		System.out.println("Depth: " + depth);
		System.out.println(board);
		if(gameService.isWinner(board, 1))
			return new SearchResult(Integer.MAX_VALUE,null);
		if(gameService.isWinner(board,2))
			return new SearchResult(Integer.MIN_VALUE, null);
		if(depth == MAX_DEPTH)
		{
			return new SearchResult(heuristicFunction.compute(board), null);
		}
		List<Move> availableMoves = gameService.getAvailableMoves(board, player);
		Move selectedMove = null;
		int value=0;
		if(Occupation.FIRST_PLAYER.hasValue(player))
		{
			value = Integer.MIN_VALUE;
			for(Move move : availableMoves)
			{
				Board nextBoard = gameService.makeMove(board, move);
				int nextBoardEvaluation = minimax(nextBoard,
				                                  depth + 1,
				                                  gameService.getNextPlayer(player)).getEvaluation() - depth;
				if (nextBoardEvaluation > value)
				{
					value        = nextBoardEvaluation;
					selectedMove = move;
				}
			}
		}
		else
		{
			value = Integer.MAX_VALUE;
			for(Move move : availableMoves)
			{
				Board nextBoard = gameService.makeMove(board, move);
				int nextBoardEvaluation = minimax(nextBoard,
				                                  depth + 1,
				                                  gameService.getNextPlayer(player)).getEvaluation() + depth;
				if (nextBoardEvaluation < value)
				{
					value        = nextBoardEvaluation;
					selectedMove = move;
				}
			}
		}
		return new SearchResult(value, selectedMove);
	}
	
}
