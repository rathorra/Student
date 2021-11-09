package igt;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtentBaseClass extends baseClass
{
	
	@Test
	public void titletest()
	{
		test=report.startTest("titletest");
		String title =driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title,"Google");
	}
	@Test
	public void clickonGmail()
	{
		test=report.startTest("clickonGmail");
		driver.findElement(By.xpath("//a[text()='Gmail']")).click();
		String text=driver.findElement(By.xpath("//td[@align='center']")).getText();
		//String Name= driver.getTitle();
		Assert.assertEquals(text,"IGT Solutions");
	}

}
