package nachos.proj1.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper
{
	private static final String DEFAULT_FORMAT = "MMM dd yyyy HH:mm:ss";
	private static final String TRANSACTION_FROMAT = "MMM dd yyyy";
	private static final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
	
	public static String getFormattedDate(Date date)
	{
		sdf.applyPattern(DEFAULT_FORMAT);
		return sdf.format(date);
	}
	
	public static String getTransactionFormattedDate(Date date)
	{
		sdf.applyPattern(TRANSACTION_FROMAT);
		return sdf.format(date);
	}
}
