package pozzi.valerio.testfabrick.model.transaction;

import pozzi.valerio.testfabrick.model.transaction.TransactionResponsePayload;

import java.util.Arrays;

/*
TODO: Same as BalanceResponse.class
*/

public class TransactionsResponse {

    private String status;
    private String[] error;
    private TransactionResponsePayload payload;

    public TransactionsResponse() {
    }

    public TransactionsResponse(String status, String[] error, TransactionResponsePayload payload) {
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

    public String[] getError() {
        return error;
    }

    public void setError(String[] error) {
        this.error = error;
    }

    public TransactionResponsePayload getPayload() {
        return payload;
    }

    public void setPayload(TransactionResponsePayload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "TransactionsResponseBody{" +
                "status='" + status + '\'' +
                ", error=" + Arrays.toString(error) +
                ", payload=" + payload +
                '}';
    }
}
