package ru.netology.Page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class PaymentMethodPage {
    private static String host = System.getProperty("host");

    SelenideElement buyButton = $$(".button__content").find(exactText("Купить"));
    SelenideElement buyCreditButton = $$(".button__content").find(exactText("Купить в кредит"));
    SelenideElement cardPayment = $$(".heading_theme_alfa-on-white").find(exactText("Оплата по карте"));
    SelenideElement creditAccordingToTheCard = $$(".heading_theme_alfa-on-white").find(exactText("Кредит " +
            "по данным карты"));

    public void buyByDebit() {
        open(host);
        buyButton.click();
        cardPayment.shouldBe(visible);
    }

    public void buyInCredit() {
        open(host);
        buyCreditButton.click();
        creditAccordingToTheCard.shouldBe(visible);
    }
}
