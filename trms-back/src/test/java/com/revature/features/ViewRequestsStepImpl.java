package com.revature.features;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewRequestsStepImpl {
	public static WebDriver driver;
	
	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
	}
	
	@Given("the user is on his profile")
	public void the_user_is_on_his_profile() {
		driver.get("C:\\Users\\carlo\\OneDrive\\Documents\\revature-training\\projects\\Project1_TRMS\\p1-trms-Carlo-Del-Mundo\\front-end\\home.html");
//	    throw new io.cucumber.java.PendingException();
	}

	@When("the requests are loaded")
	public void the_requests_are_loaded() {
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

	@Then("the table has his requests in it")
	public void the_table_has_his_requests_in_it() {
		List<WebElement> tableData = driver.findElements(By.tagName("td"));
	    String text = tableData.get(1).getText();
	    
	    assertFalse(tableData.isEmpty());
//	    throw new io.cucumber.java.PendingException();
	}
	
	@AfterAll
	public static void closeDriver() {
		driver.quit();
	}
}
