import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static java.lang.String.valueOf;

public class OrderCard {
    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void requestCardDelivery() {
        String date = generateDate(3);
        $("[data-test-id= city] input").setValue("Пенза");
        $("[data-test-id= date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id= date] input").setValue(date);
        $("[data-test-id= name] input").setValue("Тиньков Олег");
        $("[data-test-id= phone] input").setValue("+79995553333");
        $("[data-test-id= agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").shouldHave(exactText("Успешно! " + "Встреча успешно забронирована на " + date), Duration.ofSeconds(15)).shouldBe(visible);

    }
}
