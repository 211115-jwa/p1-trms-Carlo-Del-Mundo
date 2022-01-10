package com.revature.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutStepImpl {
	public static WebDriver driver;

	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
	}
	
	@Given("the user is already logged in")
	public void the_user_is_already_logged_in() {
		driver.get("C:\\Users\\carlo\\OneDrive\\Documents\\revature-training\\projects\\Project1_TRMS\\p1-trms-Carlo-Del-Mundo\\front-end\\home.html");

		WebElement toggleLogin = driver.findElement(By.id("toggleLogin"));
		toggleLogin.click();
		WebElement usernameInput = driver.findElement(By.id("txtUsername"));
		WebElement passwordInput = driver.findElement(By.id("txtPassword"));
		usernameInput.sendKeys("dsewart3@statcounter.com");
		passwordInput.sendKeys("Qm8Gxnko");
		WebElement btnLogin = driver.findElement(By.id("btnLogin"));
		btnLogin.click();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("requestsTable"), 1));
//	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the user exits his profile")
	public void the_user_exits_his_profile() {
		WebElement btnLogOut = driver.findElement(By.id("btnLogOut"));
		btnLogOut.click();

		WebElement fullName = driver.findElement(By.id("fullName"));
		
		assertEquals("Firstname Lastname", fullName);
//	    throw new io.cucumber.java.PendingException();
	}

}
