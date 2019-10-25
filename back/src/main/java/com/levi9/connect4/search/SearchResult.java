package com.levi9.connect4.search;

import com.levi9.connect4.entity.Move;

public class SearchResult
{
	private int  evaluation;
	private Move movetoMake;
	
	public SearchResult(int evaluation, Move movetoMake)
	{
		this.evaluation = evaluation;
		this.movetoMake = movetoMake;
	}
	
	public int getEvaluation()
	{
		return evaluation;
	}
	
	public void setEvaluation(int evaluation)
	{
		this.evaluation = evaluation;
	}
	
	public Move getMovetoMake()
	{
		return movetoMake;
	}
	
	public void setMovetoMake(Move movetoMake)
	{
		this.movetoMake = movetoMake;
	}
}
