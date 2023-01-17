import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @AfterAll
    void tearDown() {
            driver.quit();
        }

    @Test
    void shouldSendRequest() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.cssSelector("[class] form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Коновалова Мария");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79997776655");
        form.findElement(By.cssSelector("[data-test-id=agreement")).click();
        form.findElement(By.cssSelector("type=button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("data-test-id=order-success")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
}
