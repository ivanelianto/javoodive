package nachos.proj1.utilities;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Random;

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

	public String generateString(int length)
	{
		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		while (sb.length() < length)
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
