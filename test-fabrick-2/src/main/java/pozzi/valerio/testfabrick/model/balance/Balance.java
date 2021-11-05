package pozzi.valerio.testfabrick.model.balance;


/*TODO:
-use lombok;
-change date to correct date format (LocalDate? ZonedDateTime?);
-balance and availableBalance to BigDecimal;
-currency to enum;
-add custom annotation for validation(?)
*/


public class Balance {

    private String date;
    private String balance;
    private String availableBalance;
    private String currency;

    public Balance() {
    }

    public Balance(String date, String balance, String availableBalance, String currency) {
        this.date = date;
        this.balance = balance;
        this.availableBalance = availableBalance;
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "date='" + date + '\'' +
                ", balance='" + balance + '\'' +
                ", availableBalance='" + availableBalance + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
