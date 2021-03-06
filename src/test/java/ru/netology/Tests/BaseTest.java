package ru.netology.Tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.Data.DBUtils;
import ru.netology.Page.FormPage;
import ru.netology.Page.PaymentMethodPage;

public class BaseTest {
    protected FormPage formPage;
    protected PaymentMethodPage paymentMethodPage;

    @BeforeEach
    protected void setUpPage() {
        paymentMethodPage = new PaymentMethodPage();
        formPage = new FormPage();
    }

    @BeforeAll
    protected static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    protected void clearAll() {
        DBUtils.clearAllData();
    }

    @AfterAll
    protected static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
}
