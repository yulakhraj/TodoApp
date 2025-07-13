package com.todoapp.integration;

import com.todoapp.config.TestServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TodoIntegrationTest {
    private TestServer server;
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        // Start the embedded server with dynamic port
        server = new TestServer();
        server.start();
        baseUrl = "http://localhost:" + server.getPort();

        // Configure Chrome in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        
        // Initialize the WebDriver
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (server != null) {
            server.stop();
        }
    }

    @Test
    public void testAddTodo() {
        driver.get(baseUrl);
        
        // Add a new todo
        WebElement input = driver.findElement(By.name("task"));
        input.sendKeys("Test Todo Item");
        
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        
        // Wait for the page to refresh and verify the todo was added
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> todoItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li")));
        
        assertEquals(1, todoItems.size());
        assertEquals("Test Todo Item", todoItems.get(0).getText());
    }

    @Test
    public void testAddMultipleTodos() {
        driver.get(baseUrl);
        
        // Add multiple todos
        String[] todos = {"First Todo", "Second Todo", "Third Todo"};
        
        for (String todo : todos) {
            WebElement input = driver.findElement(By.name("task"));
            input.sendKeys(todo);
            
            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
            submitButton.click();
            
            // Wait for the page to refresh
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li")));
        }
        
        // Verify all todos were added
        List<WebElement> todoItems = driver.findElements(By.cssSelector("li"));
        assertEquals(todos.length, todoItems.size());
        
        for (int i = 0; i < todos.length; i++) {
            assertEquals(todos[i], todoItems.get(i).getText());
        }
    }

    @Test
    public void testEmptyTodoValidation() {
        driver.get(baseUrl);
        
        // Try to submit an empty todo
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        
        // Verify that no todo was added
        List<WebElement> todoItems = driver.findElements(By.cssSelector("li"));
        assertEquals(0, todoItems.size());
    }
}
