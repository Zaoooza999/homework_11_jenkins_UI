import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPageTests {
    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserVersion = "128.0";
        Configuration.browser = "chrome";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
    @Test
    void fullAutorizationTest() {
        open("/automation-practice-form");
        executeJavaScript("document.querySelector('footer').remove();");
        executeJavaScript(
                "const banner = document.querySelector('#fixedban');"
                        +
                        "if (banner) {banner.remove}");
        $(".text-center").shouldHave(text("Practice Form"));
        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Egorov");
        $("#userEmail").setValue("alex@egorov.com");
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("1.jpg");
        $("#currentAddress").setValue("Some address 1");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();
        $(".modal-content").should(appear).shouldHave(text("Thanks for submitting the form"));
        $(byText("Student Name")).parent().shouldHave(text("Alex Egorov"));
        $(byText("Student Email")).parent().shouldHave(text("alex@egorov.com"));
        $(".modal-content").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
    }
}
