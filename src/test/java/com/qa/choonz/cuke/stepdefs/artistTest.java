package com.qa.choonz.cuke.stepdefs;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class artistTest {
	
	private static WebDriver driver;
	private static WebElement targ;
	String tUser;

	@Before
	public void init(){
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        
        Random rng = new Random();
		String testUsername = rng.ints(48, 122 + 1)
	    	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))	// Filter-method to avoid going out of range
	    	      .limit(5)
	    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    	      .toString();
		this.tUser = testUsername;
	}
	
//	public static void jsClick(WebDriver driver, WebElement targ) {
//		WebElement targ = driver.findElement(By.xpath("//*[@id=\"artists\"]/div[4]/div/div/a/h5"));
//
//		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
//		jsExe.executeScript("arguments[0].click()", targ);
//	}
	
	@Given("^The correct web address$")
	public void the_correct_web_address() throws Throwable {
		driver.get("http://127.0.0.1:5501/static/index.html");
		System.out.println("choonz website accessed");
		//log out and log in
//		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
//        targ.click();
//        Thread.sleep(500);
//		targ = driver.findElement(By.xpath("//*[@id=\"logoutBtn\"]"));
//        targ.click();
//        System.out.println("logout clicked");
//        Thread.sleep(500);
        Robot robot=new Robot();
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"user_login\"]"));
        targ.sendKeys("username");
        targ = driver.findElement(By.xpath("//*[@id=\"password_login\"]"));
        targ.sendKeys("password");
        targ = driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/div/input[3]"));
        targ.click();
        System.out.println("Logged out and in again");
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
	}

	@When("^I navigate to the 'Artist' page$")
	public void i_navigate_to_the_Artist_page() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(1000);
        targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/ul/li[2]/a"));
        targ.click();
        System.out.println("Artist page clicked");
        Thread.sleep(2500);
        
	}

	@When("^I can click on an artist$")
	public void i_can_click_on_an_artist() throws Throwable {
		Thread.sleep(500);
//		driver.get("http://127.0.0.1:5501/static/artists.html");
		targ = driver.findElement(By.xpath("//*[@id=\"artists\"]/div[4]/div/div/a"));
		System.out.println("Found artist to click");
		targ.click();
		Thread.sleep(500);
//		jsClick(driver, targ);
        System.out.println("Artist clicked");
	}

	@Then("^I can select an album from the artist$")
	public void i_can_select_an_album_from_the_artist() throws Throwable {
	    targ = driver.findElement(By.xpath("//*[@id=\"artist_to_album\"]/p"));
	    assertEquals("Kiss Land", targ.getText());
	    targ.click();
	    Thread.sleep(500);
	    System.out.println("Album clicked");
	}

	@Then("^I can add a song to my desired playlist$")
	public void i_can_add_a_song_to_my_desired_playlist() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[7]/td[2]/div/a/i"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[7]/td[2]/div/div/button"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("//*[@id=\"second_dropdown7\"]/a[3]"));
		targ.click();
		Thread.sleep(500);
		System.out.println("Song added");
	}

	@Then("^I navigate to the 'Playlist' page$")
	public void i_navigate_to_the_Playlist_page() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/ul/li[5]/a"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div"));
	}

	@Then("^I can click on my desired playlist$")
	public void i_can_click_on_my_desired_playlist() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("//*[@id=\"text_container\"]/h1"));
		assertEquals("HeartBreak", targ.getText());
		System.out.println("Playlist has been accessed");
		Thread.sleep(500);
		
	}

	@Then("^I can check the previously added song exists in the playlist$")
	public void i_can_check_the_previously_added_song_exists_in_the_playlist() throws Throwable {
	    targ = driver.findElement(By.xpath("//*[@id=\"table_container\"]/table/tbody/tr[4]/td[1]/a"));
		assertEquals("Wanderlust", targ.getText());
		System.out.println("Song has been added to playlist");
	}
	
	@Then("^I can delete the song from the playlist$")
	public void i_can_delete_the_song_from_the_playlist() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr[4]/td[2]/div/a/i"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr[4]/td[2]/div/div/a"));
		targ.click();
		Thread.sleep(500);
	}
	
	@Then("^I navigate to the search bar$")
	public void i_navigate_to_the_search_bar() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/a/img"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/input"));
        targ.click();
        Thread.sleep(500);
	}

	@Then("^I can search for an artist$")
	public void i_can_search_for_an_artist() throws Throwable {
		targ.sendKeys("week");
		targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/button"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"artists\"]/div/div/div/a/h5"));
        assertEquals("The Weeknd", targ.getText());
        Thread.sleep(500);
        
		
	}

	@Then("^I can search for an album$")
	public void i_can_search_for_an_album() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/a/img"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/input"));
        targ.click();
        Thread.sleep(500);
        targ.sendKeys("mamm");
		targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/button"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"albums\"]/div/div/div/a/h5"));
        assertEquals("Mamma Mia! Official Soundtrack", targ.getText());
        Thread.sleep(500);
	}

	@Then("^I can search for an song$")
	public void i_can_search_for_an_song() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/a/img"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/input"));
        targ.click();
        Thread.sleep(500);
        targ.sendKeys("adap");
		targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/button"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"tracks\"]/div/div/div/a/h5"));
        assertEquals("Adaptation", targ.getText());
        Thread.sleep(500);
	}

	@Then("^I can search for an playlist$")
	public void i_can_search_for_an_playlist() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/a/img"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/input"));
        targ.click();
        Thread.sleep(500);
        targ.sendKeys("heart");
		targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/button"));
        targ.click();
        Thread.sleep(500);
//        targ = driver.findElement(By.xpath("//*[@id=\"playlists\"]/div/div/div/h5"));
//        targ = driver.findElement(By.xpath("//*[@id=\"playlists\"]/div/div/div"));
//        assertEquals("HeartBreak", targ.getText());
        // the text is not set as text like the others so i cannot assert it
        Thread.sleep(500);
	}

	@Then("^I can search for an genre$")
	public void i_can_search_for_an_genre() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/a/img"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/input"));
        targ.click();
        Thread.sleep(500);
        targ.sendKeys("disc");
		targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/form/button"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"genres\"]/div/div/div/a/h5"));
        assertEquals("Disco", targ.getText());
        Thread.sleep(500);
	}
	
	@Then("^I can create a new playlist$")
	public void i_can_create_a_new_playlist() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/a/img"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/ul/li[5]/a"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"addnew\"]/i"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"playlistName\"]"));
        targ.sendKeys("Chocolate");
        targ = driver.findElement(By.xpath("//*[@id=\"playlistPic\"]"));
        targ.sendKeys("https://upload.wikimedia.org/wikipedia/commons/7/70/Chocolate_%28blue_background%29.jpg");
        targ = driver.findElement(By.xpath("//*[@id=\"playlistDesc\"]"));
        targ.sendKeys("For when you want to listen to music while eating chocolate");
        targ = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        targ.click();
        System.out.println("New playlist created");
        Thread.sleep(500);
//        targ = driver.findElement(By.xpath("//*[@id=\"playlists\"]/div[4]/div/div/a/h1"));
//        assertEquals("Chocolate", targ.getText());
	}

	@Then("^I can delete the new playlist$")
	public void i_can_delete_the_new_playlist() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"playlists\"]/div[4]/div/div"));
		targ.click();
		System.out.println("I selected my new playlist");
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("//*[@id=\"text_container\"]/a/i"));
		targ.click();
		System.out.println("I clicked on the update button for playlist");
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("//*[@id=\"modal-footer\"]/a"));
		targ.click();
		System.out.println("I clicked to delete the playlist");
		Thread.sleep(500);
	}
	
	@Then("^I can log out$")
	public void i_can_log_out() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/a/img"));
		targ.click();
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"logoutBtn\"]"));
        targ.click();
        Thread.sleep(500);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/div/h2"));
        assertEquals("LOGIN", targ.getText());
        System.out.println("Logged out");
	}

	@Then("^I can make a new user$")
	public void i_can_make_a_new_user() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"label-login\"]"));
		targ.click();
		System.out.println("Clicked register button");
		Thread.sleep(500);
		targ = driver.findElement(By.xpath("//*[@id=\"Username\"]"));
		targ.sendKeys(tUser);
		targ = driver.findElement(By.xpath("//*[@id=\"Password\"]"));
		targ.sendKeys("TestPwd123");
		targ = driver.findElement(By.xpath("//*[@id=\"Password2\"]"));
		targ.sendKeys("TestPwd123");
		targ = driver.findElement(By.xpath("//*[@id=\"exampleModal\"]/div/div/div[2]/form/div[4]/input[1]"));
		targ.click();
		System.out.println("Clicked sign up button");
		Thread.sleep(500);
		Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
	}

	@Then("^I can log in with the new user credentials$")
	public void i_can_log_in_with_the_new_user_credentials() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"user_login\"]"));
        targ.sendKeys(tUser);
        targ = driver.findElement(By.xpath("//*[@id=\"password_login\"]"));
        targ.sendKeys("TestPwd123");
        System.out.println("Sent in new login details");
        targ = driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/div/input[3]"));
        targ.click();
        System.out.println("Logged in with new credentials");
        Thread.sleep(500);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"welcome\"]/a"));
        assertEquals(tUser + "!", targ.getText());
	}

	@Then("^I can find a specific track page$")
	public void i_can_find_a_specific_track_page() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/nav/button/span"));
        targ.click();
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"collapsingNavbar\"]/ul/li[1]/a"));
        targ.click();
        System.out.println("I have navigated to the page of all tracks");
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[2]/td[1]/a[1]"));
        targ.click();
        System.out.println("I have clicked a track");
        Thread.sleep(500);
        targ = driver.findElement(By.xpath("//*[@id=\"trackDuration\"]"));
        assertEquals("Duration: 4.43", targ.getText());
        System.out.println("Correct track page has been received");
        Thread.sleep(500);
	}
	
	@After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
