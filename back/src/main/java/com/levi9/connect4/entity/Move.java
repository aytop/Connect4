package com.levi9.connect4.entity;

public class Move
{
	private int player;
	private Column column;
	
	public Move(int player, Column column)
	{
		this.column = column;
		this.player = player;
	}
	
	public int getPlayer()
	{
		return player;
	}
	
	public Column getColumn()
	{
		return column;
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
