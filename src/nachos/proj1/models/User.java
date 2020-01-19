package nachos.proj1.models;

import nachos.proj1.utilities.Concealer;

public class User
{
	private static final int ID_LENGTH = 33;
	private static final String USER_FIELD_DELIMETER = "#";
	private String id;
	private String username;
	private String name;
	private int balance;

	public User()
	{
		this.id = Concealer.getInstance().generateString(ID_LENGTH);
	}
	
	public User(String rawData)
	{
		String[] fields = rawData.split(USER_FIELD_DELIMETER);
		
		this.id = fields[0];
		this.username = fields[1];
		this.name = fields[2];
		this.balance = Integer.parseInt(fields[3]);
	}

	public User(String username, String name, int balance)
	{
		this();
		this.username = username;
		this.name = name;
		this.balance = balance;
	}

	public User(String id, String username, String name, int balance)
	{
		this.id = id;
		this.username = username;
		this.name = name;
		this.balance = balance;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}
	
	public void printUserInfo()
	{
		System.out.println(String.format("ID       : %s", this.id));
		System.out.println(String.format("Username : %s", this.username));
		System.out.println(String.format("Name     : %s", this.name));
	}
	
	@Override
	public String toString()
	{
		return this.id;
	}
}
