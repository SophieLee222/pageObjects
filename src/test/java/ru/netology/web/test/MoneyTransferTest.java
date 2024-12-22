package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPageTopUp;
import ru.netology.web.page.DashboardPageYourCards;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

public class MoneyTransferTest {
    DashboardPageYourCards dashYourCards;
    String firstCardId;
    String secondCardId;
    int firstCardBalance;
    int secondCardBalance;

    @BeforeEach
    void setup() {
        var info = getAuthInfo();
        var verifCode = DataHelper.getVerificationCode();
        firstCardId = getCard1Info().getId();
        secondCardId = getCard2Info().getId();
        Selenide.open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verifPage = loginPage.validLogin(info);
        verifPage.validVerification(verifCode);
        dashYourCards = new DashboardPageYourCards();
        firstCardBalance = dashYourCards.getCardBalance(firstCardId);
        secondCardBalance = dashYourCards.getCardBalance(secondCardId);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecond() {
        var sum = generateValidAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - sum;
        var expectedBalanceSecondCard = secondCardBalance + sum;
        dashYourCards.chooseCardForTopUp(secondCardId);
        var topUpPage = new DashboardPageTopUp();
        topUpPage.moneyTransfer(secondCardId, sum, firstCardId);
        var cardFromNewBalance = dashYourCards.getCardBalance(firstCardId);
        var cardToNewBalance = dashYourCards.getCardBalance(secondCardId);
        assertEquals(expectedBalanceFirstCard, cardFromNewBalance);
        assertEquals(expectedBalanceSecondCard, cardToNewBalance);
    }

    @Test
    void shouldNotTransferMoneyWithSumMoreThanBalance() {
        var sum = generateInvalidAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance;
        var expectedBalanceSecondCard = secondCardBalance;
        dashYourCards.chooseCardForTopUp(secondCardId);
        var topUpPage = new DashboardPageTopUp();
        topUpPage.moneyTransfer(secondCardId, sum, firstCardId);
        var cardFromNewBalance = dashYourCards.getCardBalance(firstCardId);
        var cardToNewBalance = dashYourCards.getCardBalance(secondCardId);
        assertEquals(expectedBalanceFirstCard, cardFromNewBalance);
        assertEquals(expectedBalanceSecondCard, cardToNewBalance);
    }
}
