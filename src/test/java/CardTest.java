import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSendRequest() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Коновалова Мария");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997776655");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.tagName("p")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
}

    // @Test
   // void shouldSendRequest2() {
   //     driver.get("http://localhost:9999");
   //     driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Коновалова Мари-Эн");
    //    driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997776655");
    //    driver.findElement(By.className("checkbox__box")).click();
    //    driver.findElement(By.tagName("button")).click();

    //    String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
   //     String actual = driver.findElement(By.tagName("p")).getText().trim();

     //   Assertions.assertEquals(expected, actual);
 //   }
// }