package com.levi9.connect4.entity;

public enum Column
{
	A(0), B(1), C(2), D(3), E(4), F(5), G(6);
	
	private int columnOridnal;
	
	Column(int column)
	{
		this.columnOridnal =column;
	}
	
	public int getColumnOridnal()
	{
		return this.columnOridnal;
	}
	public static Column getByOrdinal(int ordinal)
	{
		switch (ordinal)
		{
			case 0:
				return Column.A;
			case 1:
				return Column.B;
			case 2:
				return Column.C;
			case 3:
				return Column.D;
			case 4:
				return Column.E;
			case 5:
				return Column.F;
			case 6:
				return Column.G;
			default:
				return null;
		}
	}
}
