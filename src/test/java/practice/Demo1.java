package practice;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Demo1 {
	@Test
	public void asserts()
	{
		assertFalse("shri".contains("shri"));
	}
	public void softasserts()
	{ 
		System.out.println("start");
		SoftAssert soft=new SoftAssert();
		soft.assertTrue("shri".contains("shri1"));
		System.out.println("It will execute remaining lines if assertion failed");
		soft.assertAll();
	}
	
}