package com.example;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginTest {

    public static void main(String[] args) throws InterruptedException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "../web_drivers/chromedriver");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the web page
        driver.get("https://www.facebook.com");
        // driver.navigate().to("https://www.facebook.com");

        // Wait for elements to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // maximize browser window
        driver.manage().window().maximize();


        // enter username
        driver.findElement(By.name("email")).sendKeys("8076452716");

        Thread.sleep(5000);
        
        // enter password
        driver.findElement(By.name("pass")).sendKeys("ASH07win!");

        // click on login button
        driver.findElement(By.name("login"));

        // Close the browser
        driver.quit();
    }
}

// step 1: navigate to the url
// step 2: login username and password, click on login button
// step 3: select india from dropdown