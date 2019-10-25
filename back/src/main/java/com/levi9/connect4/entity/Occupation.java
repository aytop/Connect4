package com.levi9.connect4.entity;

public enum Occupation
{
	FIRST_PLAYER(1),
	SECOND_PLAYER(2),
	NONE(0);
	
	private int numberValue;
	
	Occupation(int value)
	{
		this.numberValue = value;
	}
	public boolean hasValue(int numberValue)
	{
		return this.numberValue == numberValue;
	}
	public boolean isNotOtherPlayer(int numberValue){
		return this.numberValue==numberValue || this.numberValue==0;
	}
	public int getNumberValue()
	{
		return numberValue;
	}
}
