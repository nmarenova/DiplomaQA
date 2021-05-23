package ru.netology.Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.Models.CardModel;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormPage {
    private static String host = System.getProperty("host");

    SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    SelenideElement year = $(byText("Год")).parent().$(".input__control");
    SelenideElement cardOwner = $(byText("Владелец")).parent().$(".input__control");
    SelenideElement cvcOrCvvNumber = $(byText("CVC/CVV")).parent().$(".input__control");
    SelenideElement continueButton = $$(".button__content").find(exactText("Продолжить"));
    SelenideElement success = $(withText("Успешно"));
    SelenideElement error = $(withText("Ошибка"));
    SelenideElement successSearch = $$(".notification__title").find(exactText("Успешно"));
    SelenideElement errorSearch = $$(".notification__title").find(exactText("Ошибка"));
    SelenideElement inputSub = $(".input__sub");

    public void checkMessageSuccess() {
        successSearch.shouldBe(Condition.visible, Duration.ofSeconds(15));
        success.shouldBe(Condition.appear);
    }

    public void checkMessageError() {
        errorSearch.shouldBe(Condition.visible, Duration.ofSeconds(15));
        error.shouldBe(Condition.appear);
    }

    public void compareExpectedAndActualResult(String expected) {
        inputSub.shouldHave(text(expected));
        String actual = inputSub.innerText();
        assertEquals(expected, actual);
    }

    public void setCardNumber(String number) {
        cardNumber.setValue(number);
    }

    public void setCardMonth(String months) {
        month.setValue(months);
    }

    public void setCardYear(String years) {
        year.setValue(years);
    }

    public void setCardOwner(String owner) {
        cardOwner.setValue(owner);
    }

    public void setCardCVV(String cvv) {
        cvcOrCvvNumber.setValue(cvv);
    }

    public void pushContinueButton() {
        continueButton.click();
    }

    public void fillCardData(CardModel cardModel) {
        setCardNumber(cardModel.getNumber());
        setCardMonth(cardModel.getMonth());
        setCardYear(cardModel.getYear());
        setCardOwner(cardModel.getOwner());
        setCardCVV(cardModel.getCvc());
    }
}
