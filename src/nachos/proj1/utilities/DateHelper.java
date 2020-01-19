package nachos.proj1.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper
{
	private static final String DEFAULT_FORMAT = "MMM dd yyyy HH:mm:ss";
	private static final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
	
	public static String getFormattedDate(Date date)
	{
		return sdf.format(date);
	}
}
