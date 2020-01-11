package nachos.proj1.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper
{
	private static final String DEFAULT_FORMAT = "MMM dd yyyy HH:mm:ss";
	
	public static String getCurrentFormattedDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		
		return sdf.format(new Date());
	}
}
