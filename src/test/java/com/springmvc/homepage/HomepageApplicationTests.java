package com.springmvc.homepage;

import com.springmvc.homepage.POJO.User;
import com.springmvc.homepage.POJOTest.ChatPage;
import com.springmvc.homepage.POJOTest.LoginPage;
import com.springmvc.homepage.POJOTest.SignupPage;
import com.springmvc.homepage.service.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomepageApplicationTests {
	@LocalServerPort
	private Integer port;
	private SignupPage signupPage;
	private LoginPage loginPage;
	private ChatPage chatPage;

//	how do i inject this @Service application context?
	private UserService userService;

	private static WebDriver driver;

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@BeforeEach
	public void beforeEach() {
		driver.get("http://localhost:" + port);
		signupPage = new SignupPage(driver);
		loginPage = new LoginPage(driver);
		chatPage = new ChatPage(driver);
	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
	}

	@Test
	public void homePageToLoginRedirect() {
		assertEquals("http://localhost:" + port + "/login", driver.getCurrentUrl());
	}

	@Test
	public void signupUser() {
		loginPage.getBackToSignup().click();
		signupPage.submitSignup(
				"onnamani",
				"chosen",
				"Obinna",
				"Nnamani");

		assertEquals(
				"You've signed up successfully. Please login to continue",
				loginPage.getFlashMessage());
	}

	@Test
	public void loginUser(UserService userService) {
		User userObject = new User(
				null,
				"onnamani",
				null,
				"chosen",
				"Obinna",
				"Nnamani"
		);

		this.userService = userService;

		this.userService.createUser(userObject);

		loginPage.login("onnamani", "chosen");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		assertEquals("onnamani", auth.getName());
		assertEquals("http://localhost:" + port + "/chat", driver.getCurrentUrl());

	}

}
