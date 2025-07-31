package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationWithPageObjectTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Tag("demoqa")
    @DisplayName("Позитивный: Проверка успешной регистрации при заполнении всех полей")
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .hideAd()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("Ivanov@123.com")
                .setPhone("89999990000")
                .setCurrentAddress("Some address 123")
                .setGender("Male")
                .setDateOfBirth("24", "February", "1999")
                .setSubject("Physics")
                .setSubject("English")
                .setHobbies("Sports")
                .uploadPicture("photo1.jpg")
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmit();


        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Ivan Ivanov")
                .verifyResult("Student Email", "Ivanov@123.com")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "8999999000")
                .verifyResult("Date of Birth", "24 February,1999")
                .verifyResult("Subjects", "Physics, English")
                .verifyResult("Hobbies", "Sports")
                .verifyResult("Picture", "photo1.jpg")
                .verifyResult("Address", "Some address 123")
                .verifyResult("State and City", "NCR Delhi");
    }

    @Test
    @DisplayName("Позитивный: Проверка успешной регистрации при заполнении только обязательных полей")
    void successfulRegistrationMinimalTest() {
        registrationPage.openPage()
                .hideAd()
                .setFirstName("Oleg")
                .setLastName("Olegov")
                .setGender("Male")
                .setPhone("8777777777")
                .clickSubmit();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Oleg Olegov")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "8777777777");
    }

    @Test
    @DisplayName("Негативный: Проверка невозможности регистрации при незаполненнии обязательных полей")
    void negativeRegistrationTest() {
        registrationPage.openPage()
                .hideAd()
                .setFirstName("Oleg")
                .setLastName("Olegov")
                .setGender("Male")
                .clickSubmit();

        registrationPage.verifyResultsModalNotAppears();
    }
}