package nachos.proj1.models;

import nachos.proj1.utilities.Concealer;

public class Menu
{
	private static final int ID_LENGTH = 10;
	private String id;
	private String name;
	private int sellPrice;

	public Menu()
	{
		this.id = Concealer.getInstance().generateString(ID_LENGTH);
	}

	public Menu(String name, int sellPrice)
	{
		this();
		this.name = name;
		this.sellPrice = sellPrice;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSellPrice()
	{
		return sellPrice;
	}

	public void setSellPrice(int sellPrice)
	{
		this.sellPrice = sellPrice;
	}
}
