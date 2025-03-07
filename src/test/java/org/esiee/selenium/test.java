package org.esiee.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class test {
    private WebDriver driver;
    private String baseUrl = "http://localhost:8082/ObjectXChange";

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @Test
    public void testInscription() {
        driver.get(baseUrl + "/index.jsp");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-register-show")));
        registerButton.click();

        WebElement nameInput = driver.findElement(By.id("ipt-register-name"));
        WebElement emailInput = driver.findElement(By.id("ipt-register-email"));
        WebElement passwordInput = driver.findElement(By.id("ipt-register-password"));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-register-submit")));

        nameInput.sendKeys("TicTac");
        emailInput.sendKeys("TicTac@lhorloge.com");
        passwordInput.sendKeys("AliSuperMan2030?");
        submitButton.click();

//        String expectedUrl = baseUrl + "/index.jsp?success=registered";
        String expectedUrl = baseUrl + "/index.jsp?error=exists";
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void testConnexion() {
        driver.get(baseUrl + "/index.jsp");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-login-show")));
        loginButton.click();

        WebElement emailInput = driver.findElement(By.id("ipt-login-email"));
        WebElement passwordInput = driver.findElement(By.id("ipt-login-password"));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-login-submit")));

        emailInput.sendKeys("TicTac@lhorloge.com");
        passwordInput.sendKeys("AliSuperMan2030?");
        submitButton.click();

        WebElement welcomeMessage = driver.findElement(By.id("txt-welcome")); // todo: vérifier la page
        assertEquals("Bienvenue, TicTac", welcomeMessage.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
