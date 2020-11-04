package com.qa.choonz.cuke.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class testArtistsSelinium {

	private static WebDriver driver;
	private static WebElement targ;
	
	@Before
	public void init(){
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
	}
	
	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://127.0.0.1:5501/static/index.html");
		
		System.out.println("choonz website accessed");
	}
	
	@When("^I navigate to the artist page")
	public void I_navigate_to_the_Artist_page() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/ul/li[2]/a"));
        targ.click();
        System.out.println("Artist clicked");
        targ = driver.findElement(By.xpath("//*[@id=\"artists\"]/div[4]/div/div/a/h5"));
        assertEquals("The Weeknd", targ.getText());
	}
	
	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
