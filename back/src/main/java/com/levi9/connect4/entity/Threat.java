package com.levi9.connect4.entity;

public class Threat
{
	private Square[] squares;
	private int numberOfCompleted;
	private Square threateningSquare;
	
	public Threat(Square[] squares)
	{
		this.squares           = squares;
		numberOfCompleted=0;
		for(int i =0;i<4;i++)
		{
			if(squares[i].getOccupation().hasValue(0))
			{
				threateningSquare = squares[i];
			}
			else
			{
				numberOfCompleted++;
			}
		}
	}
	
	public Square[] getSquares()
	{
		return squares;
	}
	
	public int getNumberOfCompleted()
	{
		return numberOfCompleted;
	}
	
	public Square getThreateningSquare()
	{
		return threateningSquare;
	}
	public boolean isOdd()
	{
		return this.threateningSquare.isOdd();
	}
	public boolean isEven()
	{
		return !this.isOdd();
	}
}
