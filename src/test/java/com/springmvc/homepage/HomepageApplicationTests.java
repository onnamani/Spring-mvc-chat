package com.springmvc.homepage;

import com.springmvc.homepage.POJOTest.ChatPage;
import com.springmvc.homepage.POJOTest.LoginPage;
import com.springmvc.homepage.POJOTest.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomepageApplicationTests {

	@LocalServerPort
	private Integer port;

	private SignupPage signupPage;
	private LoginPage loginPage;
	private ChatPage chatPage;

	private static WebDriver driver;


	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
	}

	@BeforeEach
	public void beforeEach() {
		signupPage = new SignupPage(driver);
		loginPage = new LoginPage(driver);
		chatPage = new ChatPage(driver);
	}

	@Test
	public void signupUser() {
		driver.get("http://localhost:" + port + "/signup");
		signupPage.submitSignup(
				"jsmith",
				"12345",
				"john",
				"smith");

		assertEquals(
				"You've signed up successfully. Please login to continue",
				loginPage.getFlashMessage());
	}

	@Test
	public void loginUser() {
		driver.get("http://localhost:" + port + "/signup");
		signupPage.submitSignup(
				"krogers",
				"12345",
				"kenneth",
				"rogers"
		);

		driver.get("http://localhost:" + port);
		loginPage.login("krogers", "12345");
		assertEquals("http://localhost:" + port + "/chat", driver.getCurrentUrl());
	}

	@Test
	public void userChats() {
		driver.get("http://localhost:" + port + "/signup");
		signupPage.submitSignup(
				"obi",
				"12345",
				"Obi",
				"Nnam"
		);
		loginPage.login("obi", "12345");
		chatPage.PostChat("Hey you what's up?", "Shout");
		chatPage.getLogout().click();

		driver.get("http://localhost:" + port + "/signup");
		signupPage.submitSignup(
				"ogo",
				"12345",
				"Ogo",
				"Nnam"
		);
		loginPage.login("ogo", "12345");
		chatPage.PostChat("@obi, stop shouting please", "Whisper");

		assertEquals(2, chatPage.getChats().size());
		assertEquals("HEY YOU WHAT'S UP?", chatPage.getChatMessage().get(0).getText());
		assertEquals("obi", chatPage.getChatUsername().get(0).getText());
		assertEquals("ogo", chatPage.getChatUsername().get(1).getText());
	}

}
