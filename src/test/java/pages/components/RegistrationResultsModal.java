package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {

    private final SelenideElement modalWindowInput = $(".modal-dialog"),
            modalTitleInput = $(".modal-content"),
            modalResult = $(".table-responsive");

    public void verifyModalAppears() {
        final String modalTitle = "Thanks for submitting the form";

        modalWindowInput.should(appear);
        modalTitleInput.shouldHave(text(modalTitle));
    }

    public void verifyResult(String key, String value) {
        modalResult.$(byText(key))
                .parent().shouldHave(text(value));
    }

    public void verifyModalAppearsNegative() {
        modalTitleInput.shouldNot(visible);
    }
}