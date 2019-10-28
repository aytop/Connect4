package com.levi9.connect4.search;

import com.levi9.connect4.entity.Move;

public class SearchResult
{
	private int  evaluation;
	private Move moveToMake;
	
	public SearchResult(int evaluation, Move moveToMake)
	{
		this.evaluation = evaluation;
		this.moveToMake = moveToMake;
	}
	
	public int getEvaluation()
	{
		return evaluation;
	}
	
	public void setEvaluation(int evaluation)
	{
		this.evaluation = evaluation;
	}
	
	public Move getMoveToMake()
	{
		return moveToMake;
	}
	
	public void setMoveToMake(Move moveToMake)
	{
		this.moveToMake = moveToMake;
	}
}
