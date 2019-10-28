package com.levi9.connect4.entity;

public class Move
{
	private int player;
	private Column column;
	private int columnNumber;
	
	public Move(int player, Column column)
	{
		this.column = column;
		this.player = player;
		this.columnNumber = column.getColumnOridnal();
	}
	
	public int getPlayer()
	{
		return player;
	}
	
	public Column getColumn()
	{
		return column;
	}
	
	public int getColumnNumber()
	{
		return columnNumber;
	}
	
	@Override
	public String toString()
	{
		return "Move{" +
		       "player=" + player +
		       ", column=" + column.getColumnOridnal() +
		       '}';
	}
}
