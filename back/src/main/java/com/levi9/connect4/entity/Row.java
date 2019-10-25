package com.levi9.connect4.entity;

public enum Row
{
	FIRST(0),
	SECOND(1),
	THIRD(2),
	FOURTH(3),
	FIFTH(4),
	SIXTH(5);
	
	private int rowOrdinal;
	
	Row(int row)
	{
		this.rowOrdinal = row;
	}
	public int getRowOrdinal()
	{
		return this.rowOrdinal;
	}
	public static Row getByOrdinal(int ordinal)
	{
		switch (ordinal)
		{
			case 0:
				return Row.FIRST;
			case 1:
				return Row.SECOND;
			case 2:
				return Row.THIRD;
			case 3:
				return Row.FOURTH;
			case 4:
				return Row.FIFTH;
			case 5:
				return Row.SIXTH;
			default:
				return null;
		}
	}
	public Row getNext(){
		return getByOrdinal(this.rowOrdinal+1);
	}
}
