package com.pack.one;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestA {

WebDriver driver;
	
	@Test 
	public void login() throws AWTException
	{
       driver = new FirefoxDriver();
       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       driver.get("http://client.attuneinfocom.com/dominique_new/user/login");
       driver.manage().window().maximize();

       
       //set username field blank
       driver.findElement(By.id("edit-name")).sendKeys("");
       driver.findElement(By.id("edit-pass")).sendKeys("rahul");
       driver.findElement(By.id("edit-submit")).click();
       String errorText = driver.findElement(By.cssSelector("div.error h2")).getText();
       Assert.assertEquals("Error message", errorText);
       
       //set password field blank
       driver.findElement(By.id("edit-name")).sendKeys("rahul");
       driver.findElement(By.id("edit-pass")).sendKeys("");
       driver.findElement(By.id("edit-submit")).click();
       String errorText1 = driver.findElement(By.cssSelector("div.error ul li")).getText();
       Assert.assertEquals("Password field is required.", errorText1);
       
       //Enter wrong username 
       driver.findElement(By.id("edit-name")).clear();
       driver.findElement(By.id("edit-name")).sendKeys("rahul1");
       driver.findElement(By.id("edit-pass")).sendKeys("rahul");
       driver.findElement(By.id("edit-submit")).click();
       String errorText2 = driver.findElement(By.cssSelector("div.error a")).getText();
       Assert.assertEquals("Have you forgotten your password?", errorText2);
       
     
       //login here
       driver.findElement(By.id("edit-name")).clear();
       driver.findElement(By.id("edit-name")).sendKeys("rahul");
       driver.findElement(By.id("edit-pass")).sendKeys("rahul");
       driver.findElement(By.id("edit-submit")).click();
       String errorText3 = driver.findElement(By.cssSelector("h1.title")).getText();
       Assert.assertEquals("Your Info", errorText3);
       
       //logout here 
       driver.findElement(By.linkText("LOG OUT")).click();
       
       Robot r = new Robot();
       r.keyPress(KeyEvent.VK_ENTER);
       r.keyRelease(KeyEvent.VK_ENTER);
	}
	
}
