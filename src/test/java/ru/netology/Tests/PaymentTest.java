package ru.netology.Tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.Data.DataHelper;
import ru.netology.Models.CardModel;

public class PaymentTest extends BaseTest {
    private CardModel validCard;
    private CardModel invalidCard;

    @Test
    @DisplayName("Валидная карта, на которой достаточное количество денежных средств")
    void payWithApprovedCard() {
        validCard = DataHelper.generatedApprovedCard("ru");
        paymentMethodPage.buyByDebit();
        formPage.fillCardData(validCard);
        formPage.pushContinueButton();
        formPage.checkMessageSuccess();
    }

    @Test
    @DisplayName("Валидная карта, на которой достаточное количество денежных средств")
    void payCreditWithApprovedCard() {
        validCard = DataHelper.generatedApprovedCard("ru");
        paymentMethodPage.buyInCredit();
        formPage.fillCardData(validCard);
        formPage.pushContinueButton();
        formPage.checkMessageSuccess();
    }

    @Test
    @DisplayName("Оплата валидной картой, на которой недостаточное количество денежных средств")
    void payWithDeclinedCard() {
        invalidCard = DataHelper.generatedDeclinedCard("ru");
        paymentMethodPage.buyInCredit();
        formPage.fillCardData(invalidCard);
        formPage.pushContinueButton();
        formPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата валидной картой, на которой недостаточное количество денежных средств")
    void payCreditWithDeclinedCard() {
        invalidCard = DataHelper.generatedDeclinedCard("ru");
        paymentMethodPage.buyInCredit();
        formPage.fillCardData(invalidCard);
        formPage.pushContinueButton();
        formPage.checkMessageError();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Field.csv", numLinesToSkip = 1)
    void verifyPayField(String numberCard, String month, String year, String name, String cvv,
                        String expected, String message) {
        paymentMethodPage.buyByDebit();
        formPage.setCardNumber(numberCard);
        formPage.setCardMonth(month);
        formPage.setCardYear(year);
        formPage.setCardOwner(name);
        formPage.setCardCVV(cvv);
        formPage.pushContinueButton();
        formPage.compareExpectedAndActualResult(expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Field.csv", numLinesToSkip = 1)
    void verifyCreditField(String numberCard, String month, String year, String name, String cvv,
                           String expected, String message) {
        paymentMethodPage.buyInCredit();
        formPage.setCardNumber(numberCard);
        formPage.setCardMonth(month);
        formPage.setCardYear(year);
        formPage.setCardOwner(name);
        formPage.setCardCVV(cvv);
        formPage.pushContinueButton();
        formPage.compareExpectedAndActualResult(expected);
    }
}
