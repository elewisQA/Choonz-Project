package com.qa.choonz.cuke.stepdefs;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class artistTest {
	
	private static WebDriver driver;
	private static WebElement targ;

	@Before
	public void init(){
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
	}
	
	public static void jsClick(WebDriver driver, WebElement targ) {
//		WebElement targ = driver.findElement(By.xpath("//*[@id=\"artists\"]/div[4]/div/div/a/h5"));

		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("arguments[0].click()", targ);
	}
	
	@Given("^The correct web address$")
	public void the_correct_web_address() throws Throwable {
		driver.get("http://127.0.0.1:5501/static/index.html");
		
		System.out.println("choonz website accessed");
	}

	@When("^I navigate to the 'Artist' page$")
	public void i_navigate_to_the_Artist_page() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/ul/li[2]/a"));
        targ.click();
        System.out.println("Artist clicked");
        Thread.sleep(2500);
        
	}

	@When("^I can click on an artist$")
	public void i_can_click_on_an_artist() throws Throwable {
		Thread.sleep(2500);
		driver.get("http://127.0.0.1:5501/static/artists.html");
		targ = driver.findElement(By.xpath("//*[@id=\"artists\"]/div[4]/div/div/a"));
		System.out.println("Found artist to click");
//		targ.click();
		jsClick(driver, targ);
        System.out.println("Artist clicked");
	}

	@Then("^I can select an album from the artist$")
	public void i_can_select_an_album_from_the_artist() throws Throwable {
	    targ = driver.findElement(By.xpath("//*[@id=\"artist_to_album\"]/p"));
	    assertEquals("Kiss Land", targ.getText());
	}

	@Then("^I can add a song to my desired playlist$")
	public void i_can_add_a_song_to_my_desired_playlist() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I navigate to the 'Playlist' page$")
	public void i_navigate_to_the_Playlist_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I can click on my desired playlist$")
	public void i_can_click_on_my_desired_playlist() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I can check the previously added song exists in the playlist$")
	public void i_can_check_the_previously_added_song_exists_in_the_playlist() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
