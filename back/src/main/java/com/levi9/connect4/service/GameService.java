package com.levi9.connect4.service;

import com.levi9.connect4.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameService
{
	public List<Move> getAvailableMoves(Board board, int playerToMove)
	{
		List<Move> availableMoves= new ArrayList<>();
		for(int i =0;i< Board.WIDTH;i++)
		{
			Move potentialMove = new Move(playerToMove, Column.getByOrdinal(i));
			if(isMoveLegal(board, potentialMove))
			{
				availableMoves.add(potentialMove);
			}
		}
		return availableMoves;
	}
	public Board makeMove(Board board, Move move)
	{
		if(isMoveLegal(board, move))
		{
			Board resultBoard = new Board(board);
			int column = move.getColumn().getColumnOridnal();
			resultBoard.getSquare(column, board.getFirstAvailableRow()[column].getRowOrdinal())
			           .setOccupation(move.getPlayer());
			resultBoard.fillRow(column);
			return resultBoard;
		}
		return board;
	}
	public boolean isMoveLegal(Board board, Move move)
	{
		return board.getFirstAvailableRow()[move.getColumn().getColumnOridnal()] != null;
	}
	public boolean isWinner(Board board, int player)
	{
		if(checkHorizontally(board, player))
			return true;
		if(checkVertically(board, player))
			return true;
		return checkDiagonally(board, player);
	}
	
	private boolean checkHorizontally(Board board, int player)
	{
		return checkBoardForWin(board,
		                        player,
		                        Board.WIDTH-3,
		                        1,
		                        0,
		                        Board.HEIGHT,
		                        0);
	}
	private  boolean checkVertically(Board board, int player)
	{
		
		return checkBoardForWin(board,
		                        player,
		                        Board.WIDTH,
		                        0,
		                        0,
		                        Board.HEIGHT-3,
		                        1);
	}
	private boolean checkDiagonally(Board board, int player)
	{
		if(checkBoardForWin(board,
		                    player,
		                    Board.WIDTH-3,
		                    1,
		                    0,
		                    Board.HEIGHT-3 ,
		                    1)
		)
			return true;
		return checkBoardForWin(board,
		                        player,
		                        Board.WIDTH-3,
		                        1,
		                        3,
		                        Board.HEIGHT,
		                        -1);
	}
	
	public List<Threat> getThreats(Board board, int player,int diskRequired)
	{
		List<Threat> resultList =new ArrayList<>();
		//Horizontally
		resultList.addAll(checkBoardForThreats(board,
		                     player,
		                     Board.WIDTH-3,
		                     1,
		                     0,
		                     Board.HEIGHT,
		                     0,
		                     diskRequired));
		//Vertically
		resultList.addAll(checkBoardForThreats(board,
		                     player,
		                     0,
		                     Board.WIDTH,
		                     0,
		                     Board.HEIGHT-3,
		                     1,
		                     diskRequired));
		//Diagonally
		resultList.addAll(checkBoardForThreats(board,
		                     player,
		                     Board.WIDTH-3,
		                     1,
		                     0,
		                     Board.HEIGHT-3 ,
		                     1,
		                     diskRequired));
		resultList.addAll(checkBoardForThreats(board,
		                     player,
		                     Board.WIDTH-3,
		                     1,
		                     3,
		                     Board.HEIGHT,
		                     -1,
		                     diskRequired));
		return resultList;
	}
	
	private List<Threat> checkBoardForThreats(Board board,
	                       int player,
	                       int widthEnd,
	                       int widthStep,
	                       int heightStart,
	                       int heightEnd,
	                       int heightStep,
	                       int diskRequired)
	{
		List<Threat> resultList = new ArrayList<>();
		for (int width = 0; width < widthEnd; width++)
		{
			for (int height = heightStart; height < heightEnd; height++)
			{
				if (board.getSquare(width, height).getOccupation().hasValue(player))
				{
					Square[] threatSquares = new Square[4];
					threatSquares[0] = board.getSquare(width, height);
					boolean isThreat = true;
					int     inARow   = 1;
					for (int offset = 1; offset < 4 && isThreat; offset++)
					{
						isThreat = board.getSquare(width + widthStep * offset,
						                           height + heightStep * offset)
						                .getOccupation()
						                .isNotOtherPlayer(player);
						if(isThreat)
						{
							threatSquares[offset] = board.getSquare(width + widthStep * offset,
							                height + heightStep * offset);
						}
						if (board.getSquare(width + widthStep * offset,
						                    height + heightStep * offset)
						         .getOccupation()
						         .hasValue(player)
						)
						{
							inARow++;
						}
					}
					if (isThreat && inARow == diskRequired)
					{
						resultList.add(new Threat(threatSquares));
					}
				}
			}
		}
		return  resultList;
	}
	
	private boolean checkBoardForWin(Board board,
	                                 int player,
	                                 int widthEnd,
	                                 int widthStep,
	                                 int heightStart,
	                                 int heightEnd,
	                                 int heightStep)
	{
		for (int width = 0; width < widthEnd; width++)
		{
			for (int height = heightStart; height < heightEnd; height++)
			{
				if (board.getSquare(width, height).getOccupation().hasValue(player))
				{
					boolean canBeWin = true;
					for (int offset = 1; offset < 4 && canBeWin; offset++)
					{
						canBeWin = board.getSquare(width + widthStep * offset,
						                           height + heightStep * offset)
						                .getOccupation()
						                .hasValue(player);
						
					}
					if (canBeWin )
					{
						return true;
					}
				}
			}
		}
		return  false;
	}
	
	public int getNextPlayer(int player)
	{
		return 3-player;
	}
}
