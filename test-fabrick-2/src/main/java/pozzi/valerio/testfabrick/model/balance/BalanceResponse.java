package pozzi.valerio.testfabrick.model.balance;

import pozzi.valerio.testfabrick.model.balance.Balance;

import java.util.List;

/*
TODO:
-use Lombok
-maybe put Balance class as inner class?
-change error type to List<ErrorResponseModel>
-status could be an enum, but it's probably better to stay safe and use a String in this case
*/

public class BalanceResponse {

    private String status;
    private List<String> error;
    private Balance payload;

    public BalanceResponse() {
    }

    public BalanceResponse(String status, List<String> error, Balance payload) {
        this.status = status;
        this.error = error;
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public Balance getPayload() {
        return payload;
    }

    public void setPayload(Balance payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "BalanceResponseBody{" +
                "status='" + status + '\'' +
                ", error=" + error +
                ", payload=" + payload +
                '}';
    }
}
