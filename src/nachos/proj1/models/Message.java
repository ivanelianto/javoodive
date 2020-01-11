package nachos.proj1.models;

public class Message
{
	private User sender;
	private int destinationAddress;
	private String content;

	public Message(User sender, int destinationAddress, String content)
	{
		this.sender = sender;
		this.destinationAddress = destinationAddress;
		this.content = content;
	}

	public User getSender()
	{
		return sender;
	}

	public void setSender(User sender)
	{
		this.sender = sender;
	}

	public int getDestinationAddress()
	{
		return destinationAddress;
	}

	public void setDestinationAddress(int destinationAddress)
	{
		this.destinationAddress = destinationAddress;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public String toString()
	{
		return String.format("%d@@@%s@@@%s", this.destinationAddress, this.sender.toString(), this.content);
	}
}
