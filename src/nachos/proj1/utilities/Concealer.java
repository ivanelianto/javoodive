package nachos.proj1.utilities;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Concealer
{
	private static Concealer instance;

	private Concealer() {
	}

	public static Concealer getInstance()
	{
		if (instance == null)
			instance = new Concealer();
		return instance;
	}

	public String decode(String encodedText)
	{
		byte[] decodedBytes = Base64.getMimeDecoder().decode(encodedText.getBytes());
		
		String result = "";
		
		try
		{
			result = new String(decodedBytes, "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

	public String encode(String plainText)
	{
		String result = "";
		
		try
		{
			result = Base64.getMimeEncoder().encodeToString(plainText.getBytes("utf-8"));
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		return result;
	}
}
