package nachos.proj1.models;

import java.util.Random;

public class User
{
	private static final int ID_LENGTH = 33;
	private String id;
	private String username;
	private String name;
	private int balance;

	public User()
	{
		this.id = generateID();
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
		System.out.println(String.format("Balance  : %d", this.balance));
	}
	
	@Override
	public String toString()
	{
		return String.format("%s#%s#%s#%d", 
				this.id,
				this.username,
				this.name,
				this.balance);
	}

	private String generateID()
	{
		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		while (sb.length() < ID_LENGTH)
		{
			int charCode = random.nextInt('z');

			if (isAcceptableCharCode(charCode))
				sb.append((char) charCode);
		}

		String result = sb.toString();

		return result.substring(0, 1).toUpperCase() + result.substring(1);
	}

	private boolean isAcceptableCharCode(int charCode)
	{
		return isDigit(charCode) || isUppercaseLetter(charCode) || isLowercaseLetter(charCode);
	}

	private boolean isLowercaseLetter(int charCode)
	{
		return charCode >= 'a' && charCode <= 'z';
	}

	private boolean isUppercaseLetter(int charCode)
	{
		return charCode >= 'A' && charCode <= 'Z';
	}

	private boolean isDigit(int charCode)
	{
		return charCode >= '0' && charCode <= '9';
	}
}
