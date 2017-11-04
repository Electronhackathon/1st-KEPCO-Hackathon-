package kr.wonjun.electhon.models;

public class Card {
    private String token;
    private String cardNum;
    private String cardPassword;
    private String cardBirthday;
    private String cardExpiry;

    public Card(String token, String cardNum, String cardPassword, String cardBirthday, String cardExpiry) {
        this.token = token;
        this.cardNum = cardNum;
        this.cardPassword = cardPassword;
        this.cardBirthday = cardBirthday;
        this.cardExpiry = cardExpiry;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public String getCardBirthday() {
        return cardBirthday;
    }

    public void setCardBirthday(String cardBirthday) {
        this.cardBirthday = cardBirthday;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }
}
