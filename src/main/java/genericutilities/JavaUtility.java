package genericutilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public String getRequireDate(int days)
	{
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal=sim.getCalendar();
		cal.add(cal.DAY_OF_MONTH, days);
		String DateRequired = sim.format(cal.getTime());
		return DateRequired;
	}
	public String getCurrentDate() {
		Date d=new Date();
		String date=d.toString().replace(" ", "_").replace(":", "_");
		return date;
	}
	public int generateMobileNumber()
	{
		Random random=new Random();
		int randomNum = random.nextInt(900000000, 1000000000);
		return randomNum;
	}
	public int generateRandomNumber()
	{
		Random random=new Random();
		int randomNum = random.nextInt(1000);
		return randomNum;
		
	}
	public String generateEmail()
	{
		Random random=new Random();
		int randomNum = random.nextInt(1000);
		return "EKey"+randomNum+"@gmail.com";
		
	}
}
