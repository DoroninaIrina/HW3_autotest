import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;



public class TextBoxTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }
    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Irina");
        $("#lastName").setValue("Doronina");
        $("#userEmail").setValue("idoronina@mail.com");

        $("#genterWrapper").$(byText("Female")).click(); //исправлено

        $("#userNumber").setValue("9998887766");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1997");
        $x("//div[.='23']").click();

        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();

        $("#uploadPicture").uploadFile(new File("src/test/resources/img/pic.png"));

        $("#currentAddress").setValue("Russia");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();

        $("#submit").click();

        $(".modal-open").shouldHave(text("Thanks for submitting the form"));
        $(".modal-open").shouldHave(text("Irina Doronina"));
        $(".modal-open").shouldHave(text("idoronina@mail.com"));
        $(".modal-open").shouldHave(text("Female"));
        $(".modal-open").shouldHave(text("9998887766"));
        $(".modal-open").shouldHave(text("23 February,1997"));
        $(".modal-open").shouldHave(text("Arts"));
        $(".modal-open").shouldHave(text("Reading"));
        $(".modal-open").shouldHave(text("pic.png"));
        $(".modal-open").shouldHave(text("Russia"));
        $(".modal-open").shouldHave(text("NCR Noida"));

    }
}
