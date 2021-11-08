package pozzi.valerio.testfabrick.model.moneytransfer.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pozzi.valerio.testfabrick.model.moneytransfer.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyTransferOut {

    private String moneyTransferId;
    private Status status;
    private Direction direction;
    private Creditor creditor;
    private Debtor debtor;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private String createdDatetime;
    private String accountedDatetime;
    private LocalDate debtorValueDate;
    private LocalDate creditorValueDate;
    private Amount amount;
    private boolean isUrgent;
    private boolean isInstant;
    private FeeType feeType;
    private String feeAccountId;
    private List<Fee> fees;
    private boolean hasTaxRelief;

}



