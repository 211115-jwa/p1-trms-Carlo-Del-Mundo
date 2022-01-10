package com.revature.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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

public class LoginStepImpl {
	public static WebDriver driver;
	
	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
	}
	
	@Given("the user is on TRMS home page")
	public void the_user_is_on_trms_home_page() {
		driver.get("C:\\Users\\carlo\\OneDrive\\Documents\\revature-training\\projects\\Project1_TRMS\\p1-trms-Carlo-Del-Mundo\\front-end\\home.html");
	}

	@Given("the user clicks the Login button")
	public void the_user_clicks_the_login_button_to_login() {
		WebElement toggleLogin = driver.findElement(By.id("toggleLogin"));
		toggleLogin.click();
//	    throw new io.cucumber.java.PendingException();
	}

	@When("the user inputs {string} and {string} to log in")
	public void the_user_inputs_and_to_log_in(String username, String password) {
		WebElement usernameInput = driver.findElement(By.id("txtUsername"));
		WebElement passwordInput = driver.findElement(By.id("txtPassword"));
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
//	    throw new io.cucumber.java.PendingException();
	}

	@When("the user clicks the login button")
	public void the_user_clicks_the_login_button() {
		WebElement btnLogin = driver.findElement(By.id("btnLogin"));
		btnLogin.click();
//	    throw new io.cucumber.java.PendingException();
	}

	@Then("the header displays {string}")
	public void the_header_displays(String fullname) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(3))
				.pollingEvery(Duration.ofMillis(30));
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("requestsTable"), 1));
		
		WebElement fullName = driver.findElement(By.id("fullName"));
		
		assertEquals(fullname, fullName.getText());
		
		driver.findElement(By.id("btnLogOut")).click();
//	    throw new io.cucumber.java.PendingException();
	}

	@Then("the page says Username or password is incorrect")
	public void the_page_says_username_or_password_is_incorrect() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50))
				.ignoring(NoAlertPresentException.class);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		
		assertTrue(alert.getText().toLowerCase().contains("incorrect"));
		alert.accept();
//	    throw new io.cucumber.java.PendingException();
	}
	
	@AfterAll
	public static void closeDriver() {
		driver.quit();
	}

}
