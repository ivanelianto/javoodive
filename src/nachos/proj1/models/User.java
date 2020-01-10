package nachos.proj1.models;

import java.util.Random;

public class User
{
	private static final int ID_LENGTH = 33;
	private String id;
	private String username;
	private int balance;

	public User()
	{
		this.id = generateID();
	}

	public User(String username)
	{
		this();
		this.username = username;
		this.balance = 0;
	}

	public User(String username, int balance)
	{
		this();
		this.username = username;
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

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
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
		
		return result.substring(0, 1).toUpperCase() +
				result.substring(1);
	}

	private boolean isAcceptableCharCode(int charCode)
	{
		return isDigit(charCode)
			|| isUppercaseLetter(charCode)
			|| isLowercaseLetter(charCode);
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
