package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPageTopUp {
    private final SelenideElement header2 = $("h1").shouldHave(Condition.text("Пополнение карты"));
    private final SelenideElement actionTransfer = $("[data-test-id=action-transfer]");

    public DashboardPageTopUp() {
        header2.shouldBe(Condition.visible);
    }

    // перевод с определённой карты на выбранную карту произвольной суммы.
    public DashboardPageYourCards moneyTransfer(int sum, String cardFrom) {
        //заполняем сумму перевода
        $("[data-test-id=amount] input").setValue(String.valueOf(sum));
        //заполняем номер карты, откуда перевод
        $("[data-test-id=from] input").setValue(cardFrom);
        //нажимаем кнопку пополнить
        actionTransfer.click();
        return new DashboardPageYourCards();
    }
}
