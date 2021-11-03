package pozzi.valerio.testfabrick.model.transaction;

import pozzi.valerio.testfabrick.model.transaction.Transaction;

import java.util.List;

public class TransactionResponsePayload {

    private List<Transaction> list;

    public TransactionResponsePayload() {
    }

    public TransactionResponsePayload(List<Transaction> list) {
        this.list = list;
    }

    public List<Transaction> getList() {
        return list;
    }

    public void setList(List<Transaction> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TransactionResponcePayload{" +
                "list=" + list +
                '}';
    }

}
