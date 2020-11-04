package com.qa.choonz.selinium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

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
		driver.get("http://www.practiceselenium.com/welcome.html");
		
		System.out.println("tea website accessed");
	}
}
