package ru.netology.web.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    //статичные методы для хранения информации о пользователе и картах
    public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static CardInfo getCard1Info() {
        return new CardInfo("92df3f1c-a033-48e6-8390-206f6b1f56c0", "5559 0000 0000 0001");
    }

    public static CardInfo getCard2Info() {
        return new CardInfo("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "5559 0000 0000 0002");
    }

    public static int generateValidAmount(int balance) {
        return new Random().nextInt(Math.abs(balance)) +1;
    }

    //два дата класса для предоставления информации о клиенте
    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    //дата класс для предоставления информации о карте
    @Value
    public static class CardInfo {
        String id;
        String number;
    }
}
