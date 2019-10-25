package com.levi9.connect4.entity;

public class Square
{
	private Row row;
	private Column column;
	private Occupation occupation;
	
	public Square(int column, int row)
	{
		if(! (row>=0 && row<6))
			throw new IllegalArgumentException("Row must be in range [0, 5]");
		if(! (column>=0 && column < 7))
			throw new IllegalArgumentException("Column must be in range[0, 6]");
		this.row = Row.getByOrdinal(row);
		this.column = Column.getByOrdinal(column);
		this.occupation = Occupation.NONE;
	}
	public Square(Square other)
	{
		this.row = other.row;
		this.column = other.column;
		this.occupation = other.occupation;
	}
	public void setOccupation(int player)
	{
		if(player == 1)
			this.occupation = Occupation.FIRST_PLAYER;
		else if(player == 2)
			this.occupation = Occupation.SECOND_PLAYER;
		else
			throw new IllegalArgumentException("Player must be in range [1, 2]");
	}
	
	public void setOccupation(Occupation occupation)
	{
		this.occupation = occupation;
	}
	
	public Row getRow()
	{
		return row;
	}
	
	public Column getColumn()
	{
		return column;
	}
	
	public Occupation getOccupation()
	{
		return occupation;
	}
	public boolean isOdd()
	{
		return this.row.getRowOrdinal()%2==0;
	}
}
