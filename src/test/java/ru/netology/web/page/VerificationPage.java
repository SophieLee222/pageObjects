package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPageYourCards validVerification(DataHelper.VerificationCode code) {
        codeField.setValue(code.getCode());
        $("[data-test-id=action-verify]").click();
        return new DashboardPageYourCards();
    }
}
