package ru.netology.Tests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.Data.DBUtils;
import ru.netology.Data.DataHelper;
import ru.netology.Models.CardModel;

public class DBTest extends BaseTest {
    private CardModel validCard;
    private CardModel invalidCard;

    @Test
    @DisplayName("Тест дебетовой карты с проверкой в БД")
    void debitValidCardTest() {
        validCard = DataHelper.generatedApprovedCard("ru");
        paymentMethodPage.buyByDebit();
        formPage.fillCardData(validCard);
        formPage.pushContinueButton();
        formPage.checkMessageSuccess();
        DBUtils.checkRowPaymentNotNull();
        DBUtils.compareExpectedAmountWithActual(4500000);
        DBUtils.checkLastPaymentStatus("APPROVED");
        DBUtils.compareIDsPaymentAndOrder();
    }

    @Test
    @DisplayName("Тест невалидной дебетовой карты с проверкой в БД")
    void debitNotValidCardTest() {
        invalidCard = DataHelper.generatedDeclinedCard("ru");
        paymentMethodPage.buyByDebit();
        formPage.fillCardData(invalidCard);
        formPage.pushContinueButton();
        formPage.checkMessageSuccess();
        DBUtils.checkRowPaymentNotNull();
        DBUtils.compareExpectedAmountWithActual(4500000);
        DBUtils.checkLastPaymentStatus("DECLINED");
        DBUtils.compareIDsPaymentAndOrder();
    }

    @Test
    @DisplayName("Тест валидной кредитной карты с проверкой в БД")
    void creditValidCardTest() {
        validCard = DataHelper.generatedApprovedCard("ru");
        paymentMethodPage.buyInCredit();
        formPage.fillCardData(validCard);
        formPage.pushContinueButton();
        formPage.checkMessageSuccess();
        DBUtils.checkRowCreditNotNull();
        DBUtils.checkLastCreditStatus("APPROVED");
        DBUtils.compareIDsCreditAndOrder();
    }

    @Test
    @DisplayName("Тест не валидной кредитной карты с проверкой в БД")
    void creditNotValidCardTest() {
        invalidCard = DataHelper.generatedDeclinedCard("ru");
        paymentMethodPage.buyInCredit();
        formPage.fillCardData(invalidCard);
        formPage.pushContinueButton();
        formPage.checkMessageSuccess();
        DBUtils.checkRowCreditNotNull();
        DBUtils.checkLastCreditStatus("DECLINED");
        DBUtils.compareIDsCreditAndOrder();
    }
}
