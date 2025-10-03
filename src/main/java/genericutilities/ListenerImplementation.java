package genericutilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener,ISuiteListener {
	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		Date d=new Date();
		String date=d.toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReports/Report_"+date+".html");//report customized
		spark.config().setDocumentTitle("CRM Report");
		spark.config().setReportName("Ninza CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();//generate report
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows");
		report.setSystemInfo("Browser","Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();//Backup report
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testcasename=result.getMethod().getMethodName();
		//System.out.println(testcasename+"Execution Started");
		test = report.createTest(testcasename);//take the control and monitor the execution
		test.log(Status.INFO, testcasename+"Execution Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testcasename=result.getMethod().getMethodName();
		//System.out.println(testcasename+"Execution Success");
		test.log(Status.PASS, testcasename+"Execution Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testcasename=result.getMethod().getMethodName();
		BaseClass bs=new BaseClass();
		JavaUtility jlib=new JavaUtility();
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
		test.log(Status.FAIL,testcasename+"Execution Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testcasename=result.getMethod().getMethodName();
		//System.out.println(testcasename+"Execution Skipped");
		test.log(Status.SKIP, testcasename+"Execution Started");
		
	}
	

}
