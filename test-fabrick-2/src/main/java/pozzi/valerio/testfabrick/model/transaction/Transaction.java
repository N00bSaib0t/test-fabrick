package pozzi.valerio.testfabrick.model.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private String transactionId;
    private String operationId;
    private String accountingDate;
    private String valueDate;
    private TransactionType type;
    private String amount;
    private String currency;
    private String description;

    @Data
    public class TransactionType {
        private String enumeration;
        private String value;
    }

}


