package com.levi9.connect4.entity;

import java.util.Arrays;

public class Board
{
	public static final int HEIGHT = 6;
	public static final int WIDTH = 7;
	
	private Square[][] squares;
	private Row[] firstAvailableRow;
	
	public Board()
	{
		this.squares = new Square[WIDTH][HEIGHT];
		this.firstAvailableRow = new Row[WIDTH];
		for(int width = 0; width<WIDTH; width++)
		{
			this.firstAvailableRow[width] = Row.FIRST;
			for(int height = 0; height< HEIGHT; height++)
			{
				this.squares[width][height] = new Square(width, height);
			}
		}
	}
	public Board(String boardString)
	{
		if(boardString.length()!=HEIGHT*WIDTH)
		{
			throw new IllegalArgumentException("State string must be "+HEIGHT*WIDTH+" characters long");
		}
		this.squares = new Square[WIDTH][HEIGHT];
		this.firstAvailableRow = new Row[WIDTH];
		for(int i=0; i< boardString.length(); i++)
		{
			int height = i/WIDTH;
			int width = i%WIDTH;
			int player = Integer.parseInt(boardString.substring(i,i+1));
			if(player != 0 && player != 1 && player != 2)
			{
				throw  new IllegalArgumentException("Board elements must be in range [0, 2]");
			}
			this.squares[width][height] = new Square(width, height);
			if(player != 0)
			{
				this.squares[width][height].setOccupation(player);
			}
		}
		for(int width=0;width<WIDTH;width++)
		{
			for(int height=0;height< HEIGHT; height++)
			{
				this.firstAvailableRow[width] = null;
				if(squares[width][height].getOccupation()==Occupation.NONE)
				{
					this.firstAvailableRow[width] = Row.getByOrdinal(height);
					break;
				}
			}
		}
	}
	public Board(Board other)
	{
		this.squares = new Square[WIDTH][HEIGHT];
		this.firstAvailableRow = new Row[WIDTH];
		for(int width=0; width< WIDTH;width++)
		{
			this.firstAvailableRow[width] = other.firstAvailableRow[width];
			for(int height=0; height< HEIGHT; height++)
			{
				this.squares[width][height] = new Square(other.squares[width][height]);
			}
		}
	}
	public void setOccupation(int width, int height, int player)
	{
		this.squares[width][height].setOccupation(player);
	}
	public Square getSquare(int width, int height)
	{
		return this.squares[width][height];
	}
	
	public Row[] getFirstAvailableRow()
	{
		return firstAvailableRow;
	}
	public void fillRow(int column)
	{
		this.firstAvailableRow[column] = firstAvailableRow[column].getNext();
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int h = HEIGHT-1; h >=0; h--)
		{
			for (int w = 0; w < WIDTH; w++)
			{
				sb.append(squares[w][h].getOccupation().getNumberValue());
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
