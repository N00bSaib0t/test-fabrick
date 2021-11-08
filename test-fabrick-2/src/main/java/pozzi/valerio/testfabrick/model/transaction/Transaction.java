package pozzi.valerio.testfabrick.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String transactionId;
    private String operationId;
    private LocalDate accountingDate;
    private LocalDate valueDate;
    private TransactionType type;
    private BigDecimal amount;
    private String currency;
    private String description;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionType {
        private String enumeration;
        private String value;
    }

}


