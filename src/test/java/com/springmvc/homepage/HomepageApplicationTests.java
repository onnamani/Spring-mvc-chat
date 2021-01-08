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

}
