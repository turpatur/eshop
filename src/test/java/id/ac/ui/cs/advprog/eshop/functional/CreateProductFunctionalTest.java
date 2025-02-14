package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String listUrl;
    private String createUrl;

    @BeforeEach
    void setup() {
        String baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
        listUrl = baseUrl + "/product/list";
        createUrl = baseUrl + "/product/create";
    }

    @Test
    void createButtonGoToCreatePage (ChromeDriver driver){
        driver.get(listUrl);
        WebElement createPageButton = driver.findElement(By.linkText("Create Product"));
        createPageButton.click();
        String currentUrl = driver.getCurrentUrl();

        assertEquals(createUrl, currentUrl);
    }

    @Test
    void testPageTitle(ChromeDriver driver) {
        driver.get(listUrl);
        String pageTitle = driver.getTitle();

        assertEquals("Product List", pageTitle);
    }

    /*
    Check headers and columns
     */
    @Test
    void testProductListDisplay(ChromeDriver driver) {
        driver.get(listUrl);

        String header = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Product List", header);

        List<WebElement> productListColumn = driver.findElements(By.tagName("th"));
        String[] expectedColumns = {"Product Name", "Quantity", "Actions"};

        assertEquals(expectedColumns.length, productListColumn.size());

        for(int i=0; i<expectedColumns.length; i++){
            WebElement correspondingColumn = productListColumn.get(i);
            String expectedColumnName = expectedColumns[i];
            assertEquals(expectedColumnName, correspondingColumn.getText());
        }
    }

    /*
    Fill the form, then check if entry is already in the table
     */
    @Test
    void testCreateProduct(ChromeDriver driver){
        driver.get(createUrl);
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        String productName = "Product Test";
        nameInput.clear();
        nameInput.sendKeys(productName);

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        String productQuantity = "100";
        quantityInput.clear();
        quantityInput.sendKeys(productQuantity);

        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(listUrl, currentUrl);

        List<WebElement> tdTags = driver.findElements(By.tagName("td"));

        // productName, productQuantity, actions
        assertEquals(3, tdTags.size());

        String savedProductName = tdTags.get(0).getText();
        assertEquals(productName, savedProductName);

        String savedProductQuantity = tdTags.get(1).getText();
        assertEquals(productQuantity, savedProductQuantity);
    }
}