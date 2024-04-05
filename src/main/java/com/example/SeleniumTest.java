package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "../web_drivers/chromedriver");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the web page
        driver.get("https://www.test.com");

        // Wait for elements to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Click on Table Data button
        WebElement tableDataButton = driver.findElement(By.id("table-data-page-button"));
        tableDataButton.click();

        // Find the input text box and enter the JSON data
        WebElement inputTextBox = driver.findElement(By.id("data"));
        inputTextBox.sendKeys("[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, " +
                "{\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, " +
                "{\"name\": \"Sara\", \"age\" : 42, \"gender\": \"female\"}, " +
                "{\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, " +
                "{\"name\": \"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]");

        // Click on Refresh Table button
        WebElement refreshButton = driver.findElement(By.id("load-table"));
        refreshButton.click();

        // Get the table rows and assert the data
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='table2']/tbody/tr"));

        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            List<WebElement> columns = row.findElements(By.tagName("td"));

            String name = columns.get(0).getText();
            int age = Integer.parseInt(columns.get(1).getText());
            String gender = columns.get(2).getText();

            // Assert the data with the expected values
            switch (i) {
                case 0:
                    assertData(name, age, gender, "Bob", 20, "male");
                    break;
                case 1:
                    assertData(name, age, gender, "George", 42, "male");
                    break;
                case 2:
                    assertData(name, age, gender, "Sara", 42, "female");
                    break;
                case 3:
                    assertData(name, age, gender, "Conor", 40, "male");
                    break;
                case 4:
                    assertData(name, age, gender, "Jennifer", 42, "female");
                    break;
                default:
                    System.out.println("Invalid row index: " + i);
                    break;
            }
        }

        // Close the browser
        driver.quit();
    }

    private static void assertData(String actualName, int actualAge, String actualGender,
            String expectedName, int expectedAge, String expectedGender) {
        assert actualName.equals(expectedName);
        assert actualAge == expectedAge;
        assert actualGender.equals(expectedGender);
    }
}
