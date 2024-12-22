package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import javax.print.DocFlavor;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPageYourCards {
    private final SelenideElement header1 = $("h1").shouldHave(Condition.text("Ваши карты"));
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPageYourCards() {
        header1.shouldBe(Condition.visible);
    }

    // выбор карты для пополнения (+жмяк на копку Пополнить)
    public DashboardPageTopUp chooseCardForTopUp(String idTo) {
        cards.findBy(Condition.attribute("data-test-id", idTo)).$("button").click();
        return new DashboardPageTopUp();

    }

    // получение баланса по карте со страницы списка карт
    public int getCardBalance(String id) { //на входе имеем data-test-id нужной карты
        //выбираем элемент из коллекции по совпадению data-test-id
        val text = cards.findBy(Condition.attribute("data-test-id", id)).text();
        //получаем баланс нужной карты
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
