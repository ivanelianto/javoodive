package nachos.proj1.models;

public class TextMessage implements Message
{
	public static final String MESSAGE_PART_DELIMETER = "@@@";
	private int dstAddress;
	private User user;
	private String content;

	public TextMessage(int dstAddress, User user, String content)
	{
		this.dstAddress = dstAddress;
		this.user = user;
		this.content = content;
	}

	public void setDstAddress(int dstAddress)
	{
		this.dstAddress = dstAddress;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
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
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(MESSAGE_PART_DELIMETER);
		sb.append(this.getDstAddress());
		sb.append(MESSAGE_PART_DELIMETER);
		sb.append(this.user.toString());
		sb.append(MESSAGE_PART_DELIMETER);
		sb.append(this.content);
		
		return sb.toString();
	}

	@Override
	public int getDstAddress()
	{
		return this.dstAddress;
	}
}
