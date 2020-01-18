package nachos.proj1.models;

public class NewInstanceMessage implements Message
{
	private int srcAddress;
	private int dstAddress;
	private int userIndex;

	public NewInstanceMessage(int srcAddress, int dstAddress, int userIndex)
	{
		this.srcAddress = srcAddress;
		this.dstAddress = dstAddress;
		this.userIndex = userIndex;
	}

	public int getSrcAddress()
	{
		return srcAddress;
	}

	public void setSrcAddress(int srcAddress)
	{
		this.srcAddress = srcAddress;
	}

	public int getUserIndex()
	{
		return userIndex;
	}

	public void setUserIndex(int userIndex)
	{
		this.userIndex = userIndex;
	}

	@Override
	public String toString()
	{
		return String.format("%s@@@%d@@@%d", this.getClass().getSimpleName(), this.srcAddress, this.userIndex);
	}

	@Override
	public int getDstAddress()
	{
		return this.dstAddress;
	}
}
