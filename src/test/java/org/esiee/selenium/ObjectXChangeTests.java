package org.esiee.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

public class ObjectXChangeTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "http://localhost:8080/ObjectXChange";
    private final String user = "TicTac";
    private final String email = "TicTacToePic@lhorloge.com";
    private final String password = "AliSuperMan2030!";

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login() {
        driver.get(baseUrl + "/index.jsp");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-login-show"))).click();
        driver.findElement(By.id("ipt-login-email")).sendKeys(email);
        driver.findElement(By.id("ipt-login-password")).sendKeys(password);
        driver.findElement(By.id("btn-login-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txt-welcome")));
    }

    private void addObject(String name, String description, String image) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ajouter un objet']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal_add_object")));
        driver.findElement(By.cssSelector("#modal_add_object input[name='name']")).sendKeys(name);
        driver.findElement(By.cssSelector("#modal_add_object input[name='description']")).sendKeys(description);
        driver.findElement(By.cssSelector("#modal_add_object input[name='image']")).sendKeys(image);
        new Select(driver.findElement(By.cssSelector("#modal_add_object select[name='categoryId']"))).selectByIndex(1);
        driver.findElement(By.cssSelector("#modal_add_object button.btn-primary")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal_add_object")));
    }

    @Test
    public void testInscription() {
        driver.get(baseUrl + "/index.jsp");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-register-show"))).click();
        driver.findElement(By.id("ipt-register-name")).sendKeys(user);
        driver.findElement(By.id("ipt-register-email")).sendKeys(email);
        driver.findElement(By.id("ipt-register-password")).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-register-submit"))).click();
        assertEquals(baseUrl + "/?error=exists", driver.getCurrentUrl());
    }

    @Test
    public void testConnexion() {
        login();
        String welcome = driver.findElement(By.id("txt-welcome")).getText();
        assertEquals("Bienvenue, " + user, welcome);
    }

    @Test
    public void testSearchObjectByName() {
        driver.get(baseUrl + "/index.jsp");
        WebElement searchInput = driver.findElement(By.name("name"));
        searchInput.clear();
        searchInput.sendKeys("Produit");
        driver.findElement(By.xpath("//button[text()='Filtrer']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-title")));
        List<WebElement> titles = driver.findElements(By.className("card-title"));
        assertTrue("Le produit recherché n'est pas affiché",
                titles.stream().anyMatch(e -> e.getText().contains("Produit")));
    }

    @Test
    public void testSearchObjectByCategory() {
        driver.get(baseUrl + "/");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@name='categoryId']/option"), 1));
        Select categorySelect = new Select(driver.findElement(By.name("categoryId")));
        categorySelect.selectByIndex(1);
        String category = categorySelect.getFirstSelectedOption().getText();
        driver.findElement(By.xpath("//button[text()='Filtrer']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-body")));
        List<WebElement> cards = driver.findElements(By.className("card-body"));
        assertTrue("Aucun produit avec la catégorie " + category + " n'est affiché",
                cards.stream().anyMatch(card -> card.getText().contains(category)));
    }

    @Test
    public void testUserMakeExchange() {
        driver.get(baseUrl + "/");
        login();
        String objectName = "MonObjetTest";
        addObject(objectName, "Objet de test", "image");
        driver.get(baseUrl);
        List<WebElement> exchangeButtons = driver.findElements(
                By.xpath("//button[contains(text(),'Proposer un échange') and not(contains(@class,'btn-disabled'))]"));
        assertFalse("Aucun produit échangeable trouvé", exchangeButtons.isEmpty());
        exchangeButtons.get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal_product_client")));
        List<WebElement> radios = driver.findElements(By.cssSelector("input[type='radio'][name='productIdOffered']"));
        WebElement targetRadio = radios.stream()
                .filter(radio -> radio.findElement(By.xpath("./ancestor::tr")).getText().contains(objectName))
                .findFirst().orElse(null);
        assertNotNull("Le produit ajouté n'est pas dans la liste", targetRadio);
        targetRadio.click();
        driver.findElement(By.xpath("//button[text()='Proposer cet échange']")).click();
        wait.until(ExpectedConditions.urlContains("success=exchangeCreated"));
        assertTrue(driver.getCurrentUrl().contains("success=exchangeCreated"));
    }

    @Test
    public void testUserAcceptExchange() {
        // À implémenter ultérieurement
    }
}
