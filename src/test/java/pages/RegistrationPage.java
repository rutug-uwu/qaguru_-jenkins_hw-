package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userPhoneInput = $("#userNumber"),
            userAddressInput = $("#currentAddress"),
            genderWrapper = $("#genterWrapper"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            stateInput = $("#state"),
            stateCityWrapper = $("#stateCity-wrapper"),
            cityInput = $("#city"),
            submitButton = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();

    @Step("Открываем страницу формы регистрации")
    public RegistrationPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    @Step("Убираем рекламу со страницы")
    public RegistrationPage hideAd() {

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        return this;
    }

    @Step("Указываем имя пользователя")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Указываем фамилию пользователя")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Указываем почту пользвоателя")
    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Указываем номер телефона пользователя")
    public RegistrationPage setPhone(String value) {
        userPhoneInput.setValue(value);

        return this;
    }

    @Step("Указываем текущий адрес проживания пользователя")
    public RegistrationPage setCurrentAddress(String value) {
        userAddressInput.setValue(value);

        return this;
    }

    @Step("Указываем пол пользователя")
    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    @Step("Указываем дату рождения пользователя")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    @Step("Указываем предметы пользователя")
    public RegistrationPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Указываем хобби пользователя")
    public RegistrationPage setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();

        return this;
    }

    @Step("Загружаем картинку-аватар пользователя")
    public RegistrationPage uploadPicture(String value) {
        pictureInput.uploadFromClasspath(value);

        return this;
    }

    @Step("Выбираем штат пользователя")
    public RegistrationPage setState(String state) {
        stateInput.click();
        stateCityWrapper.$(byText(state)).click();

        return this;
    }

    @Step("Выбираем город пользователя")
    public RegistrationPage setCity(String city) {
        cityInput.click();
        stateCityWrapper.$(byText(city)).click();

        return this;
    }

    @Step("Нажимаем на кнопку Submit")
    public void clickSubmit() {
        submitButton.click();

    }

    @Step("Проверяем, что появляется окно об успешном заполнении формы регистрации")
    public RegistrationPage verifyResultsModalAppears() {
        registrationResultsModal.verifyModalAppears();

        return this;
    }

    @Step("Проверяем, что поле {key} заполнено правильно")
    public RegistrationPage verifyResult(String key, String value) {
        registrationResultsModal.verifyResult(key, value);

        return this;
    }

    @Step("Проверяем, что окно об успешной заполнении формы регистрации не появляется")
    public void verifyResultsModalNotAppears() {
        registrationResultsModal.verifyModalAppearsNegative();

    }
}
